package com.hotelium.mainservice.util;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue implements Serializable {
    private String key;
    private String value;
    private String additionalData;

    public KeyValue(String key, String value) {
        this.setKey(key);
        this.setValue(value);
        this.setAdditionalData("");
    }
}