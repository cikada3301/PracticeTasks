package com.example.crudrestapiapp.model;

import com.example.crudrestapiapp.model.eventlisteners.EventTaskListener;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@EntityListeners(EventTaskListener.class)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "completed")
    private Boolean completed;
}
