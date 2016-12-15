package com.bill.domain.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by huangbin on 16-12-10.
 */
@Entity
@Table(name = "t_resource")
@Data
public class Resource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "resource_name", nullable = false, length = 20, unique = true, updatable = false)
    private String resourceName;

    @Column(name = "resource_desc", nullable = false, length = 100)
    private String resourceDesc;

    @OneToMany(targetEntity = Resource.class, cascade = CascadeType.ALL, mappedBy = "parent")
    List<Resource> resourceList;

    @Column(name = "resource_level", precision = 1)
    private Long resourceLevel;

    @Column(name = "resource_url", nullable = false,length = 50)
    private String resourceUrl;


    @Column(name = "resource_icon", nullable = false,length = 50)
    private String resourceIcon;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    Resource parent;

    @Column(name = "expanded", nullable = false)
    private String expanded;

    @Column(name = "status", nullable = false,length = 1)
    private Boolean  status;
}
