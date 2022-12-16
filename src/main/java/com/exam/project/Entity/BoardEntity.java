package com.exam.project.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BoardEntity {

    @Id
    private long boardId;

    @Column
    private String nameKo;
}
