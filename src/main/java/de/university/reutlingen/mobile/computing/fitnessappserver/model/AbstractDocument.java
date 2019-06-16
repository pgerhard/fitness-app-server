package de.university.reutlingen.mobile.computing.fitnessappserver.model;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class AbstractDocument<P extends Serializable> implements Persistable<P> {

    @Id
    private P id;

    @NotNull
    private UUID identifier;

    @CreatedDate
    private LocalDateTime createdDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @Override
    public P getId () {
        return this.id;
    }

    @Override
    public boolean isNew () {
        return Objects.isNull ( this.id );
    }

    public void setId ( P id ) {
        this.id = id;
    }

    public UUID getIdentifier () {
        return identifier;
    }

    public void setIdentifier ( UUID identifier ) {
        this.identifier = identifier;
    }

    public LocalDateTime getCreatedDate () {
        return createdDate;
    }

    public void setCreatedDate ( LocalDateTime createdDate ) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy () {
        return createdBy;
    }

    public void setCreatedBy ( String createdBy ) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getLastModifiedDate () {
        return lastModifiedDate;
    }

    public void setLastModifiedDate ( LocalDateTime lastModifiedDate ) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy () {
        return lastModifiedBy;
    }

    public void setLastModifiedBy ( String lastModifiedBy ) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
