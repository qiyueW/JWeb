package system.web.validate.model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wangchunzi
 */
public class InitValidateModel {

    ValidateModelData vmd = new ValidateModelData();

    public void doinitData(Class<? extends ValidateModel> c) {
        try {
            ValidateModel x = c.newInstance();
            x.iniValidate();
            vmd.putMap(c, x);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(InitValidateModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ValidateModel get(Class<? extends ValidateModel> c) {
        return vmd.get(c);
    }
}
