package com.hotelium.mainservice.serviceview.impl;

import com.hotelium.mainservice.domain.Room;
import com.hotelium.mainservice.dto.RoomReadDTO;
import com.hotelium.mainservice.dto.RoomSearchCriteriaDTO;
import com.hotelium.mainservice.dto.RoomWriteDTO;
import com.hotelium.mainservice.service.RoomService;
import com.hotelium.mainservice.serviceview.RoomServiceView;
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
public class RoomServiceViewImpl implements RoomServiceView {
    private final RoomService roomService;
    private final ModelMapper modelMapper;

    @Override
    public RoomReadDTO create(RoomWriteDTO roomWriteDTO) {
        return convertToDTO(roomService.create(roomWriteDTO));
    }

    @Override
    public RoomReadDTO getById(Long id) {
        return convertToDTO(roomService.getById(id));
    }

    @Override
    public RoomReadDTO delete(Long id) {
        return convertToDTO(roomService.delete(id));
    }

    @Override
    public Page<RoomReadDTO> search(RoomSearchCriteriaDTO filter, Pageable pageable) {
        return roomService.search(filter, pageable).map(this::convertToDTO);
    }

    @Override
    public RoomReadDTO markAsClean(Long id) {
        return convertToDTO(roomService.markAsClean(id));
    }

    private RoomReadDTO convertToDTO(Room room) {
        return modelMapper.map(room, RoomReadDTO.class);
    }
}
