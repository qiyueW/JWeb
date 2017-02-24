package system.web.validate.config;

import java.io.IOException;
import javax.servlet.ServletException;
import system.web.JWeb;
import system.web.validate.model.ValidateModel;

/**
 *
 * @author JSON校验引擎
 */
public abstract class JsonValidateEngineModel {

    abstract public boolean doValidateAndResultError(JWeb jw, ValidateModel vm) throws ServletException, IOException, IllegalArgumentException, IllegalAccessException;

}
