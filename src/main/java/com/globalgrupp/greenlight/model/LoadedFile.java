package com.globalgrupp.greenlight.model;

import javax.persistence.*;

/**
 * Created by п on 31.12.2015.
 */

@Entity
@Table(name="files")
public class LoadedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="files_id")
    private Long id;

    @Column(name="files_data")
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoadedFile() {
    }
}

