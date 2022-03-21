package com.hotelium.mainservice.service.impl;

import com.hotelium.mainservice.domain.Room;
import com.hotelium.mainservice.dto.RoomSearchCriteriaDTO;
import com.hotelium.mainservice.dto.RoomWriteDTO;
import com.hotelium.mainservice.exception.ServiceExecutionException;
import com.hotelium.mainservice.repository.RoomRepository;
import com.hotelium.mainservice.service.RoomService;
import com.hotelium.mainservice.util.CustomRsqlVisitor;
import com.hotelium.mainservice.util.MessageUtil;
import com.hotelium.mainservice.util.SessionContext;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final MessageUtil messageUtil;

    @Override
    public Room create(RoomWriteDTO roomWriteDTO) {
        final var roomDB = modelMapper.map(roomWriteDTO, Room.class);
        roomDB.setStatus(Room.RoomStatus.CLEAN);
        return roomRepository.save(roomDB);
    }

    @Override
    public Room getById(String id) {
        final var room = roomRepository.findByIdAndOrgId(id, SessionContext.getSessionData().getOrgId());
        if (room.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("reservationMaster.roomNotFound.exception"));
        }
        return room.get();
    }

    @Override
    public Room update(String id, RoomWriteDTO roomWriteDTO) {
        final var updatedRoom = getById(id);
        modelMapper.map(roomWriteDTO, updatedRoom);
        return roomRepository.save(updatedRoom);
    }

    @Override
    public Room delete(String id) {
        final var roomDb = getById(id);
        roomRepository.deleteById(id);
        return roomDb;
    }

    @Override
    public Page<Room> search(RoomSearchCriteriaDTO filter, Pageable pageable) {
        filter.setOrgId(SessionContext.getSessionData().getOrgId());
        return roomRepository.findAll(filter.RoomSearchCriteriaFieldMapper(filter), pageable);
    }

    @Override
    public Room markAsReserved(String id) {
        final var roomDb = getById(id);
        roomDb.setStatus(Room.RoomStatus.RESERVED);
        return roomRepository.save(roomDb);
    }

    @Override
    public Room markAsFilled(String id) {
        final var roomDb = getById(id);
        roomDb.setStatus(Room.RoomStatus.FILLED);
        return roomRepository.save(roomDb);
    }

    @Override
    public Room markAsDirt(String id) {
        final var roomDb = getById(id);
        roomDb.setStatus(Room.RoomStatus.DIRTY);
        return roomRepository.save(roomDb);
    }

    @Override
    public Room markAsClean(String id) {
        final var roomDb = getById(id);
        roomDb.setStatus(Room.RoomStatus.CLEAN);
        return roomRepository.save(roomDb);
    }

    @Override
    public List<Room> find(String rsqlQueryString) {
        Node rootNode = new RSQLParser().parse(rsqlQueryString);
        Specification<Room> spec = rootNode.accept(new CustomRsqlVisitor<Room>());
        return roomRepository.findAll(spec);

    }
}
