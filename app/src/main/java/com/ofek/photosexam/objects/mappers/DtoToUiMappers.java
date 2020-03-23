package com.ofek.photosexam.objects.mappers;

import com.ofek.photosexam.objects.guruapiobjects.PhotoDto;
import com.ofek.photosexam.objects.uiobjects.UiPhoto;

public class DtoToUiMappers {

    public static UiPhoto photoMapper(PhotoDto photoDto){
        UiPhoto uiPhoto = new UiPhoto();
        uiPhoto.setGuruPhotoId(photoDto.getId());
        uiPhoto.setLikes(photoDto.getLikes());
        uiPhoto.setMaxHeight(photoDto.getHeight());
        uiPhoto.setMaxWidth(photoDto.getWidth());
        uiPhoto.setRatio(photoDto.getRatio());
        uiPhoto.setUploadDate(photoDto.getUploadDate());
        uiPhoto.setLikes(photoDto.getLikes());
        uiPhoto.setViews(photoDto.getViews());
        return uiPhoto;
    }
}
