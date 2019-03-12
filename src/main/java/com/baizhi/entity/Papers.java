package com.baizhi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "papers")
public class Papers implements Serializable {
    @Id
    private String id;
    @Column(name = "name")
    private String name;
    private String path;
    @Transient
    private String haha;
}
