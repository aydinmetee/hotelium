package com.hotelium.mainservice.service.impl;

import com.hotelium.mainservice.domain.Room;
import com.hotelium.mainservice.dto.RoomSearchCriteriaDTO;
import com.hotelium.mainservice.dto.RoomWriteDTO;
import com.hotelium.mainservice.exception.ServiceExecutionException;
import com.hotelium.mainservice.repository.RoomRepository;
import com.hotelium.mainservice.service.RoomService;
import com.hotelium.mainservice.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Room getById(Long id) {
        final var room = roomRepository.findById(id);
        if (room.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("reservationMaster.roomNotFound.exception"));
        }
        return room.get();
    }

    @Override
    public Room delete(Long id) {
        final var roomDb = getById(id);
        roomRepository.deleteById(id);
        return roomDb;
    }

    @Override
    public Page<Room> search(RoomSearchCriteriaDTO filter, Pageable pageable) {
        return roomRepository.findAll(filter.RoomSearchCriteriaFieldMapper(filter), pageable);
    }

    @Override
    public Room markAsReserved(Long id) {
        final var roomDb = getById(id);
        roomDb.setStatus(Room.RoomStatus.RESERVED);
        return roomRepository.save(roomDb);
    }

    @Override
    public Room markAsFilled(Long id) {
        final var roomDb = getById(id);
        roomDb.setStatus(Room.RoomStatus.FILLED);
        return roomRepository.save(roomDb);
    }

    @Override
    public Room markAsDirt(Long id) {
        final var roomDb = getById(id);
        roomDb.setStatus(Room.RoomStatus.DIRTY);
        return roomRepository.save(roomDb);
    }

    @Override
    public Room markAsClean(Long id) {
        final var roomDb = getById(id);
        roomDb.setStatus(Room.RoomStatus.CLEAN);
        return roomRepository.save(roomDb);
    }
}
