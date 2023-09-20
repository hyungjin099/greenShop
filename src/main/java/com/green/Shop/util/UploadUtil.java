package com.green.Shop.util;

import com.green.Shop.item.vo.ImgVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UploadUtil {
    //파일 첨부 기능(단일 파일 업로드)
    public static ImgVO uploadFile(MultipartFile img){
        ImgVO imgVO = null;

        //첨부파일을 선택했다면...
        if(!img.isEmpty()){
            imgVO = new ImgVO();

            //첨부파일
            String originFileName = img.getOriginalFilename();

            //첨부될 파일명
            String uuid = UUID.randomUUID().toString();
            //가장 빨리 만나는 자바.jpg
            int dotIndex = originFileName.lastIndexOf(".");
            String extension = originFileName.substring(dotIndex);
            String attachedFileName = uuid + extension;

            //파일 첨부
            try {
                File file = new File(ConstantVariable.UPLOAD_PATH + attachedFileName);
                img.transferTo(file);

                imgVO.setOriginFileName(originFileName);
                imgVO.setAttachedFileName(attachedFileName);
                imgVO.setIsMain("Y");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return imgVO;
    }

    //다중 파일 업로드
    public static List<ImgVO> multiFileUpload(MultipartFile[] imgs){
        List<ImgVO> imgList = new ArrayList<>();

        for(MultipartFile img : imgs){
            ImgVO vo = uploadFile(img);

            if(vo != null){
                vo.setIsMain("N");
                imgList.add(vo);
            }
        }
        return imgList;
    }
}







