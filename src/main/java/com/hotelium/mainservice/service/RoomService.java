package com.hotelium.mainservice.service;

import com.hotelium.mainservice.domain.Room;
import com.hotelium.mainservice.dto.RoomSearchCriteriaDTO;
import com.hotelium.mainservice.dto.RoomWriteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface RoomService {
    Room create(RoomWriteDTO roomWriteDTO);

    Room getById(String id);

    Room delete(String id);

    Page<Room> search(RoomSearchCriteriaDTO filter, Pageable pageable);

    Room markAsReserved(String id);

    Room markAsFilled(String id);

    Room markAsDirt(String id);

    Room markAsClean(String id);

    //TODO:STATU DEGISIMI ICIN MARK AS FONKSIYONLARI EKLENECEK.
}
