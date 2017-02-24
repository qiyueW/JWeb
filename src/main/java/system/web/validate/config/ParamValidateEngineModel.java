package system.web.validate.config;

import java.io.IOException;
import javax.servlet.ServletException;
import system.web.JWeb;
import system.web.validate.model.ValidateModel;

/**
 * 值检验引擎
 *
 * @author wangchunzi
 */
public abstract class ParamValidateEngineModel {

    abstract public boolean doValidateAndResultError(JWeb jw, ValidateModel vm) throws ServletException, IOException, IllegalArgumentException, IllegalAccessException ;
}
