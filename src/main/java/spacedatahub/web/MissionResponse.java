package spacedatahub.web;

import java.time.LocalDateTime;
import spacedatahub.struct.ImageryType;

public class MissionResponse {

    private Long id;

    private String name;

    private ImageryType imageryType;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    public MissionResponse(Long id, String name, ImageryType imageryType, LocalDateTime startDate, LocalDateTime finishDate) {
        this.id = id;
        this.name = name;
        this.imageryType = imageryType;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }
    
    public MissionResponse(String name, ImageryType imageryType, LocalDateTime startDate, LocalDateTime finishDate) {
        this.name = name;
        this.imageryType = imageryType;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public MissionResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageryType getImageryType() {
        return imageryType;
    }

    public void setImageryType(ImageryType imageryType) {
        this.imageryType = imageryType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }
}
