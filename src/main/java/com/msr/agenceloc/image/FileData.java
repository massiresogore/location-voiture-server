package com.msr.agenceloc.image;

import com.msr.agenceloc.automobile.Automobile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "FILE_DATA")
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String filePath;

    @ManyToOne(cascade = CascadeType.ALL)
    private Automobile automobile;
}
