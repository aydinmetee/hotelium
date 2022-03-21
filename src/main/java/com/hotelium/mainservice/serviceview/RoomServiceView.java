package com.hotelium.mainservice.serviceview;

import com.hotelium.mainservice.dto.RoomReadDTO;
import com.hotelium.mainservice.dto.RoomSearchCriteriaDTO;
import com.hotelium.mainservice.dto.RoomWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface RoomServiceView {
    RoomReadDTO create(RoomWriteDTO roomWriteDTO);

    RoomReadDTO getById(String id);

    RoomReadDTO update(String id, RoomWriteDTO roomWriteDTO);

    RoomReadDTO delete(String id);

    Page<RoomReadDTO> search(RoomSearchCriteriaDTO filter, Pageable pageable);

    RoomReadDTO markAsClean(String id);

    List<RoomReadDTO> find(String rsqlQueryString);

}
