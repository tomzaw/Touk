package spacedatahub.web;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import spacedatahub.struct.ImageryType;

public class CreateMissionRequest {

    @NotBlank
    private String missionName;

    @NotNull
    private ImageryType imageryType;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime finishDate;

    public CreateMissionRequest(String missionName, ImageryType imageryType, LocalDateTime startDate, LocalDateTime finishDate) {
        this.missionName = missionName;
        this.imageryType = imageryType;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public CreateMissionRequest() {
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
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
