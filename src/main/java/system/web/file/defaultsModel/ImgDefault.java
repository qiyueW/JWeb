package system.web.file.defaultsModel;

import system.web.file.FileModel;

/**
 *
 * @author wangchunzi
 */
public class ImgDefault extends FileModel {

    @Override
    public void configuration(system.web.file.temp.FileConfig fc) {
        fc.path_save = "upfile/img/";
        fc.fileNameSuffix_alloy ="jpg,jpeg,gif,png";
        fc.fileSizeThreshold = 5 * 1024 * 1024;
        fc.fileSize_max = 5 * 1024 * 1024;
    }

}
