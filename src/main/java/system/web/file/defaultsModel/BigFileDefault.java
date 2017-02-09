package system.web.file.defaultsModel;

import system.web.file.FileModel;

/**
 *
 * @author wangchunzi
 */
public class BigFileDefault extends FileModel {

    @Override
    public void configuration(system.web.file.temp.FileConfig fc) {
        fc.path_save = "upfile/img/";
        fc.fileSize_max = -1;
        fc.fileNameSuffix_alloy = "";
        fc.fileSizeThreshold=2*1024*1024;
    }
}
