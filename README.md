前言
1.JWeb框架使用servlet3.0+版本以上。无需配置web.xml文件即可自动注册拦截器。
2.支持原生Servlet3.0的Servlet各功能。
3.不差struts2分毫的拦截器（我们还是亲切点，叫回过滤器），却比其他更简单！更灵活！！更高效！！更易维护！！！
4.基本无多少学习代价。设计之初便考虑到最初的Servlet习惯！但我们做到了简单使用，不简单功能实现！！
5.一个简单的而强大的框架，往往设计上会复杂得多，并耗性能。但我们做到了简单、高速、易用相结合。这得益于作者的耐心与数学功底。
6.不只是代码，连同设计流程也一起奉献。以顺让用户完全把握框架，从而从多而杂的开源框架中，拥有自己的框架，离开框架的变动、出现bug无从下手……等等问题脱离出来，如同请了位架构师，在为您公司无偿加班了一段时间
7.本框架最大的好处，
一是基于高效的数据模型；
二是基于强大的过滤器（完全可以定制过各种同用视图！）；
三支持扫描式配置（比如/xx/xx开关的请求路径统一要经过xx过滤器/过滤组。是不是很自然地想到权限的管理呢？），
四是没有封装过度！！不会出现隔年无人会用的尴尬！而且封装过度，还消耗性能，加大维护难度，二次开发及后期人员变动，投入的预备阶段的成本！！最关键的是，没有封装过度，还非常好用、简单。

一、hello!
建一个类,加入@H与@M标注即可进行访问！通过@H+@M的值进行访问。
<<<<<<< HEAD
@H("/say")
public class HI {

    @M("/yousay")// 通过 /say/yousay访问。
    public static void selectOne(JWeb jw) {
        jw.printOne("hello!!!!!");
    }
}

二、获得用户传参
1.	直接获取。
2.	通过对象，自动装箱。
对象的属性名，就是用户传过来的参数名；属性值，就是用户传过来的参数值。
例子：
package wx.web;
import system.web.JWeb;
import system.web.hm.annotation.H;
import system.web.hm.annotation.M;
import system.web.validate.annotation.Validate;

@H("/login")
public class UserLogin {
    @M("/user")
    //@Validate(UserLoginValidate.class)//这个是校验。这个先跳过，我们一会会讲到。
    public static void selectOne(JWeb jw) {
        //直接取参数
        String account = jw.getString("account");
        String passord = jw.getString("passord");
        System.out.println("我是直接取参:" + account + "//" + passord);
//        //对象式取参
//        UserVO vo = jw.getObject(UserVO.class);
//        System.out.println("我是对象式取参:" + vo.account + "//" + vo.passord);  
 
    jw.forward("/admin/index.jsp");//假设登陆成功，进行跳转
    }
}
如果要演示对象传参，还需要以下代码：
 public class UserVO {
    public String account;
    public String passord;
}
三、校验用户传参：ValidateModel
第一步：
定义一个类，然后继承ValidateModel抽象类。
实现其中的抽象方法即可。
  public abstract void iniValidate();
  public abstract ValidateResultModel recheck(JWeb jw, Map<String, ValidateFieldModel> map, ValidateResultModel vr);
例子：

package wx.web;
import system.web.validate.model.ValidateModel;
import system.web.JWeb;
import system.web.validate.model.ValidateFieldModel;
import system.web.validate.model.ValidateResultModel;
import java.util.Map;
public class UserLoginValidate extends ValidateModel {
//    {
//        this.setReturnMsgKey(ValidateModel.VALIDATE_MSG_KEY);//这个key的作用是，存在request对象属性的key.
//        this.setReturnURL("/index.jsp");//校验不通过时，跳转的路径。
//    }
    @Override
public void iniValidate() {
//	如下中，put方法放入的
//第一个参数，跟用户传的参数名相同；
//第二个参数是正则表达式；
//第三个参数是错误提示；
//第四个参数，是否必须校验。True时为必须校验。
        super
                .put("account", "[a-zA-Z0-9]{1,12}", "账号只能用数字与字母组成。并且不能超出12位", true)
                .put("passord", "[a-zA-Z0-9]{1,16}", "密码只能用数字与字母组成。并且不能超出16位", true);
    }
    @Override
    public ValidateResultModel recheck(JWeb jw, Map<String, ValidateFieldModel> map, ValidateResultModel vr) {
        //上面的校验通过后，都才进行复检
        //当vr里有值时，默认不通过复检。（传参来的vr对象是干净的。）
		//如果不需要复检，请不要写任何代码。
		//复检的用处：首先，比如进行验证码的验证等。
        return vr;
    }
}

第二步，用上我们的校验
在需要进行校验的地方，直接使用@Validate(UserLoginValidate.class)
如用户传参例子中：
@H("/login")
public class UserLogin {
    @M("/user")
    @Validate(UserLoginValidate.class)//这个是校验。
    public static void selectOne(JWeb jw) {
……

四、文件上传
1.使用commons-fileupload控件上传支持
所以，需要加入两个jar包：commons-fileupload 与commons-io

package wx.web.filemanager;
import configuration.file.ImgFileModel;
import system.web.JWeb;
import system.web.file.FI;
import system.web.file.engine.FileEngine;
import system.web.hm.annotation.H;
import system.web.hm.annotation.M;

@H("/fileup")
public class ImgUp {

    @M("/one/img")//fileup/one/img
    public static void up(JWeb jw) {
        //初始化一个上传引擎（需要传入HttpServletRequest对象
        FileEngine file = new FileEngine(jw.request);
        //执行上传操作。默认上传第一个文件（假如有多个文件的吗)
        FI upOne = file.upOne(ImgFileModel.class);
//        java.util.List<FI> upVast = file.upVast(ImgFileModel.class);//批量上传操作
//        file.upOne(ImgFileModel.class, "file");//指定参数名上传
//        file.upVast(ImgFileModel.class, "file");//指定参数名，批传
        
        //返回json数据。注意,FI对象重写了toString,此方法现在返回的是json数据
        jw.printOne(upOne.toString());
    }
}

如上，在执行上传时，需要传入一个ImgFileModel.class，这个类必须要继承FileModel类。
如具体的配置如下完整代码：

package configuration.file;
public class ImgFileModel extends system.web.file.FileModel {

    @Override
    public void configuration(system.web.file.temp.FileConfig fc) {
        //web根目录的绝对路径.直接采用全局配置实例中，取得即可。
        fc.path_web_real = system.web.WebContext.getWebContext().WEB_PATH;
        fc.path_save = "upload/img/";            //上传的目录
        fc.fileNameSuffix_alloy = "gif,jpg,jpeg,bmp,png";//图片类型后缀
        fc.fileSize_max = 2 * 1024 * 1024;      //文件大小
        //文件缓冲，及写入硬盘临时文件的临界值
        //如果采用Servlet原生支持，两处fileSizeThreshold的值都必须一样！
        fc.fileSizeThreshold = 2 * 1024 * 1024; 
        //等其他参数。
    }
}

 
2使用Servlet原生的上传。
从代码上，我们可以看出，跟上例1一差不多。
区别就是，下面直接使用原生的Servlet(注意，下例子的 extends Servlet，实际就是，就是扩展HttpServlet.框架提供Servlet类封装，是方便使用JWeb对象。)
另外要注意的就是，一定要配置fileSizeThreshold的值。同时，还要跟配置ImgFileModel类的fileSizeThreshold一样！！
 
package wx.web.filemanager;
import configuration.file.ImgFileModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import system.web.JWeb;
import system.web.file.engine.PartEngine;
import system.web.servlet.Servlet;

@WebServlet(name = "UpImgFile", urlPatterns = {"/UpImgFile"})
@MultipartConfig(location = "", fileSizeThreshold = 2 * 1024 * 1024)
public class UpImgFile extends Servlet {

    @Override
    protected void doService(JWeb jw) throws ServletException, IOException {
        PartEngine pe = new PartEngine(jw.request);
        pe.upOne(ImgFileModel.class);
    }
}


五、定义过滤器
(1)、通过继承FilterModel，来自定义过滤器
如下。过滤默认放在顶层。如果不执行super.setLocation(LOCATION_BUTTOM);修改的话

package configuration.filter.buttom;
import system.web.JWeb;
import system.web.filter.chain.FilterModel;

public class DemoFilter extends FilterModel{
    {
        //表示此过滤器放在
        //顶层LOCATION_TOP,或中层LOCATION_CENTER,或底层LOCATION_BUTTOM
        //执行
        super.setLocation(LOCATION_BUTTOM);//底层。执行完@HM服务类或Servlet后执行的过滤器
    }
    @Override
    public void doFilter(JWeb jw) {
        //具体实现
    }   
}

(2)、通过JWFilter使用我们定义的过滤器
如下例子：通过@JWFilter(我们定义的过滤类)，所有此类的方法请求，都会被加入这个过器。
 */
@H("/login")
@JWFilter(DemoFilter.class)//看我，在类处。则所有此类方法下，都会使用我的过滤
public class UserLogin {


当然，可以直接放在方法那里，这样只作用在此方法。
 public class UserLogin {

    @M("/user")//
    @Validate(UserLoginValidate.class)
    @JWFilter(DemoFilter.class)//看我，在方法处
    public static void selectOne(JWeb jw) {


注、过滤器是单例模式的。
 
(4)、创建过滤组
为什么要创建过滤组？
比如要多个过滤器共同完成一个功能。
比如一个类、方法要配置多个不同功能的过滤器。
我们通过继承FilterModels来创建一个过滤组。如下代码。
然后加入我们其他的过滤器，或过滤组。
 
package configuration.filter;
import configuration.filter.buttom.DemoFilter;
import java.util.Set;
import system.web.filter.chain.FilterModels;

public class MyFilters extends FilterModels {

    @Override
    public void put(Set<Class> modelClass) {
        modelClass.add(DemoFilter.class);
        modelClass.add(null);//或其他过滤组的类
        //可以继续加入...
    }
}
(5)、通过JWFilter使用我们定义的过滤器组
用法与过滤器一样！可放在类处，亦可放在方法处。都是同个@JWFilter
(5)、实战过滤之定义文件下载视图！
先看成果。是的！只要把文件相对路径放入request属性中。即可实例下载了！！！为什么可以这样方便？
主要是因为我们用了自己定义的低层过滤器(在@H@M服务类-方法或Servlet后执行的过滤器) BigFileDownload.class!!
 
package wx.web.filemanager;
import system.web.JWeb;
import system.web.filter.annotation.JWFilter;
import system.web.hm.annotation.H;
import system.web.hm.annotation.M;

@H("/filedow")
public class Dow {
    @M("/big/myfile")
    @JWFilter(configuration.filter.buttom.BigFileDownload.class)
    public static void doww(JWeb jw) {
        //真实项目中，我们都是从数据库取出文件的相对路径或由用户提交要下载的文件名
        //假设我们要下载的文件放在项目upload/img下.如图所示，我们就能输出文件流给用户了！
        jw.request.setAttribute("file", "upload/img/20170208_172948_4960000001Hydrangeas.jpg");
    }
}


现在，我们所有类似的下载，都可以使用此过滤器了。
再看看我们的过滤器定义：

 package configuration.filter.buttom;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import system.web.JWeb;
import system.web.WebContext;
import system.web.filter.chain.FilterModel;

public class BigFileDownload extends FilterModel {

    {super.setLocation(LOCATION_BUTTOM); }
    public static final int buff = 1024 * 1024 * 3;
    @Override
    public void doFilter(JWeb jw) {
        Object attribute = jw.request.getAttribute("file");
        if (null != attribute) {
            String str = attribute.toString();
            jw.request.removeAttribute("file"); 
            String filename = str.substring(str.lastIndexOf("/")+1);
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(WebContext.getWebContext().WEB_PATH + str);
            } catch (FileNotFoundException ex) {
                jw.printOne("你访问的资源已经失效！");
                return;
            } 
            jw.response.reset();
            jw.response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            try {
                jw.response.setContentLength(fis.available());
            } catch (IOException ex) {
                jw.printOne("获得文件大小成错!");
                return;
            } 
            byte[] b = new byte[buff];
            int len;
            try {
                while ((len = fis.read(b)) > 0) {
                    jw.response.getOutputStream().write(b, 0, len);
                }
                jw.response.flushBuffer();
                jw.response.getOutputStream().close();
                fis.close();
            } catch (IOException e) {
            }
        }
    }
}


当然，我们可以发挥我们的想象力，定义统一的，与ui 框架统一的视图。比如说json…
(6)、使用扫描配置过滤器、过滤组！
之有我们都是使用@JWFilter来配置使用我们的过滤器、过滤组。现在，我们可以通过扫描来配置！
首先，我们要继承一个ConfigurationFilter类。
如果在实现configuration方法内，进行扫描配置，如下所示中的
lf.addFiltersByURL("/filedow/big/*", configuration.filter.buttom.BigFileDownload.class);
表示所有请求路径/filedow/big/开头的，都自动加入此过滤器！


package configuration.filter;
import system.web.filter.chain.config.ConfigurationFilter;
import system.web.filter.chain.config.LinkFilters;

public class MyConfigurationFilter extends ConfigurationFilter {

    @Override
    public void configuration(LinkFilters lf) {

//        //第一个参数[test.action.*]表示以此开头的(类+方法)路径，将绑定第二个参数所有的过滤器
//        lf.addFiltersByMethod("test.action.*", new Class[]{ 
//            //test.configuration.filter.f1.TwoFilter.class, Filters1.class, Filters8.class
//        });
//        第一个参数/x/ss表示以此开头的请求路径，将绑定第二个参数所有的过滤器
        lf.addFiltersByURL("/filedow/big/*", configuration.filter.buttom.BigFileDownload.class);
    }

}


再看先前的一例子，通过扫描配置，我们已经可以去除
@JWFilter(configuration.filter.buttom.BigFileDownload.class)的手工绑定了！！
 package wx.web.filemanager;

import system.web.JWeb;
import system.web.filter.annotation.JWFilter;
import system.web.hm.annotation.H;
import system.web.hm.annotation.M;

@H("/filedow")
public class Dow {
    @M("/big/myfile")
    public static void doww(JWeb jw) {
        //真实项目中，我们都是从数据库取出文件的相对路径或由用户提交要下载的文件名
        //假设我们要下载的文件放在项目upload/img下.如图所示，我们就能输出文件流给用户了！
        jw.request.setAttribute("file", "upload/img/20170208_172948_4960000001Hydrangeas.jpg");
    }
}



六、全局配置：WebConfigModel
我们看一下默认的配置：
基本上，我们HM_SUFFIX是重点。这是我们重点要讲的。
/**
     * 请求、响应的 字符编码
     */
    public String ENCODE = "UTF-8";

    public String CONTEXTPATH_KEY = "path_home";

    /**
     * 默认为空时，表示拦截所有的。
     * <p>
     * 如果要拦截指定后缀的请求，请以*.开头。如：*.jw
     */
    public String HM_SUFFIX = "";

    /**
     * 仅当属性HM_SUFFIX为空时，此属性才起作用。
     * <p>
     * 即：采用拦截所有的过滤方案时。
     */
    public boolean RESOURCE_MANAGER_OPEN = true;

    /**
     * SQL 值注入过滤（默认开，专对传参的值进行过滤）。如果要将sql语句发给sql执行，请将此开关设置成false
     */
    public boolean SQL_REJECT_OPEN = true;

    /**
     * 被转换的字符
     */
    public String SQL_REJECT_KEY = "'";

    /**
     * 转换后的字符
     */
    public String SQL_REJECT_VALUE = "&#39;";

    /**
     * 脚本注入过滤 因为html标签很多方括号，
     * <p>
     * 为了减少替换的性能，把方括号转换改成方括号+scr的转换
     */
    public String SCRIPT_REJECT_KEY = "<scr";

    public String SCRIPT_REJECT_VALUE = "&#60;scr";
    
    public String DATE_FORMAT = "yyyy-MM-dd";
    
public String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


下面我们来自定义配置。如，我们要修改HM_SUFFIX值。如下，表示我们只管理带.jw后缀的请求路径。
此种模式下，效率相对高些。
 package configuration;

public class WebConfigModel extends system.web.config.temp.WebConfigModel {

    @Override
    public void config(system.web.config.temp.WebConfig config) {
        config.HM_SUFFIX = "*.jw";
    }

}
=======
```
@H("/say")
public class HI {

    @M("/yousay")// 通过 /say/yousay访问。
    public static void selectOne(JWeb jw) {
        jw.printOne("hello!!!!!");
    }
}
```

二、获得用户传参
1.	直接获取。
2.	通过对象，自动装箱。
对象的属性名，就是用户传过来的参数名；属性值，就是用户传过来的参数值。
例子：
```
package wx.web;
import system.web.JWeb;
import system.web.hm.annotation.H;
import system.web.hm.annotation.M;
import system.web.validate.annotation.Validate;

@H("/login")
public class UserLogin {
    @M("/user")
    //@Validate(UserLoginValidate.class)//这个是校验。这个先跳过，我们一会会讲到。
    public static void selectOne(JWeb jw) {
        //直接取参数
        String account = jw.getString("account");
        String passord = jw.getString("passord");
        System.out.println("我是直接取参:" + account + "//" + passord);
//        //对象式取参
//        UserVO vo = jw.getObject(UserVO.class);
//        System.out.println("我是对象式取参:" + vo.account + "//" + vo.passord);  
 
    jw.forward("/admin/index.jsp");//假设登陆成功，进行跳转
    }
}
```
如果要演示对象传参，还需要以下代码：
 ```
public class UserVO {
    public String account;
    public String passord;
}
```
三、校验用户传参：ValidateModel
第一步：
定义一个类，然后继承ValidateModel抽象类。
实现其中的抽象方法即可。
  public abstract void iniValidate();
  public abstract ValidateResultModel recheck(JWeb jw, Map<String, ValidateFieldModel> map, ValidateResultModel vr);
例子：

```
package wx.web;
import system.web.validate.model.ValidateModel;
import system.web.JWeb;
import system.web.validate.model.ValidateFieldModel;
import system.web.validate.model.ValidateResultModel;
import java.util.Map;
public class UserLoginValidate extends ValidateModel {
//    {
//        this.setReturnMsgKey(ValidateModel.VALIDATE_MSG_KEY);//这个key的作用是，存在request对象属性的key.
//        this.setReturnURL("/index.jsp");//校验不通过时，跳转的路径。
//    }
    @Override
public void iniValidate() {
//	如下中，put方法放入的
//第一个参数，跟用户传的参数名相同；
//第二个参数是正则表达式；
//第三个参数是错误提示；
//第四个参数，是否必须校验。True时为必须校验。
        super
                .put("account", "[a-zA-Z0-9]{1,12}", "账号只能用数字与字母组成。并且不能超出12位", true)
                .put("passord", "[a-zA-Z0-9]{1,16}", "密码只能用数字与字母组成。并且不能超出16位", true);
    }
    @Override
    public ValidateResultModel recheck(JWeb jw, Map<String, ValidateFieldModel> map, ValidateResultModel vr) {
        //上面的校验通过后，都才进行复检
        //当vr里有值时，默认不通过复检。（传参来的vr对象是干净的。）
		//如果不需要复检，请不要写任何代码。
		//复检的用处：首先，比如进行验证码的验证等。
        return vr;
    }
}
```

第二步，用上我们的校验
在需要进行校验的地方，直接使用@Validate(UserLoginValidate.class)
如用户传参例子中：
```
@H("/login")
public class UserLogin {
    @M("/user")
    @Validate(UserLoginValidate.class)//这个是校验。
    public static void selectOne(JWeb jw) {
……
```

四、文件上传
1.使用commons-fileupload控件上传支持
所以，需要加入两个jar包：commons-fileupload 与commons-io

```
package wx.web.filemanager;
import configuration.file.ImgFileModel;
import system.web.JWeb;
import system.web.file.FI;
import system.web.file.engine.FileEngine;
import system.web.hm.annotation.H;
import system.web.hm.annotation.M;

@H("/fileup")
public class ImgUp {

    @M("/one/img")//fileup/one/img
    public static void up(JWeb jw) {
        //初始化一个上传引擎（需要传入HttpServletRequest对象
        FileEngine file = new FileEngine(jw.request);
        //执行上传操作。默认上传第一个文件（假如有多个文件的吗)
        FI upOne = file.upOne(ImgFileModel.class);
//        java.util.List<FI> upVast = file.upVast(ImgFileModel.class);//批量上传操作
//        file.upOne(ImgFileModel.class, "file");//指定参数名上传
//        file.upVast(ImgFileModel.class, "file");//指定参数名，批传
        
        //返回json数据。注意,FI对象重写了toString,此方法现在返回的是json数据
        jw.printOne(upOne.toString());
    }
}
```

如上，在执行上传时，需要传入一个ImgFileModel.class，这个类必须要继承FileModel类。
如具体的配置如下完整代码：

```
package configuration.file;
public class ImgFileModel extends system.web.file.FileModel {

    @Override
    public void configuration(system.web.file.temp.FileConfig fc) {
        //web根目录的绝对路径.直接采用全局配置实例中，取得即可。
        fc.path_web_real = system.web.WebContext.getWebContext().WEB_PATH;
        fc.path_save = "upload/img/";            //上传的目录
        fc.fileNameSuffix_alloy = "gif,jpg,jpeg,bmp,png";//图片类型后缀
        fc.fileSize_max = 2 * 1024 * 1024;      //文件大小
        //文件缓冲，及写入硬盘临时文件的临界值
        //如果采用Servlet原生支持，两处fileSizeThreshold的值都必须一样！
        fc.fileSizeThreshold = 2 * 1024 * 1024; 
        //等其他参数。
    }
}

```
 
2使用Servlet原生的上传。
从代码上，我们可以看出，跟上例1一差不多。
区别就是，下面直接使用原生的Servlet(注意，下例子的 extends Servlet，实际就是，就是扩展HttpServlet.框架提供Servlet类封装，是方便使用JWeb对象。)
另外要注意的就是，一定要配置fileSizeThreshold的值。同时，还要跟配置ImgFileModel类的fileSizeThreshold一样！！
 
```
package wx.web.filemanager;
import configuration.file.ImgFileModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import system.web.JWeb;
import system.web.file.engine.PartEngine;
import system.web.servlet.Servlet;

@WebServlet(name = "UpImgFile", urlPatterns = {"/UpImgFile"})
@MultipartConfig(location = "", fileSizeThreshold = 2 * 1024 * 1024)
public class UpImgFile extends Servlet {

    @Override
    protected void doService(JWeb jw) throws ServletException, IOException {
        PartEngine pe = new PartEngine(jw.request);
        pe.upOne(ImgFileModel.class);
    }
}

```

五、定义过滤器
(1)、通过继承FilterModel，来自定义过滤器
如下。过滤默认放在顶层。如果不执行super.setLocation(LOCATION_BUTTOM);修改的话

```
package configuration.filter.buttom;
import system.web.JWeb;
import system.web.filter.chain.FilterModel;

public class DemoFilter extends FilterModel{
    {
        //表示此过滤器放在
        //顶层LOCATION_TOP,或中层LOCATION_CENTER,或底层LOCATION_BUTTOM
        //执行
        super.setLocation(LOCATION_BUTTOM);//底层。执行完@HM服务类或Servlet后执行的过滤器
    }
    @Override
    public void doFilter(JWeb jw) {
        //具体实现
    }   
}
```


 
我们看一下通过super.setLocation能配置的容间位置 一共三个。
 
(2)、通过JWFilter使用我们定义的过滤器
如下例子：通过@JWFilter(我们定义的过滤类)，么，所有此类的方法请求，都会被加入这个过器。
 
```
@H("/login")
@JWFilter(DemoFilter.class)//看我，在类处。则所有此类方法下，都会使用我的过滤
public class UserLogin {

```

当然，可以直接放在方法那里，这样只作用在此方法。
 ```
public class UserLogin {

    @M("/user")//
    @Validate(UserLoginValidate.class)
    @JWFilter(DemoFilter.class)//看我，在方法处
    public static void selectOne(JWeb jw) {
```


(3)、过滤器是单例模式的。
 
(4)、创建过滤组
为什么要创建过滤组？
比如要多个过滤器共同完成一个功能。
比如一个类、方法要配置多个不同功能的过滤器。
我们通过继承FilterModels来创建一个过滤组。如下代码。
然后加入我们其他的过滤器，或过滤组。
 
```
package configuration.filter;
import configuration.filter.buttom.DemoFilter;
import java.util.Set;
import system.web.filter.chain.FilterModels;

public class MyFilters extends FilterModels {

    @Override
    public void put(Set<Class> modelClass) {
        modelClass.add(DemoFilter.class);
        modelClass.add(null);//或其他过滤组的类
        //可以继续加入...
    }
}
```
(5)、通过JWFilter使用我们定义的过滤器组
用法与过滤器一样！可放在类处，亦可放在方法处。都是同个@JWFilter
(5)、实战过滤之定义文件下载视图！
先看成果。是的！只要把文件相对路径放入request属性中。即可实例下载了！！！为什么可以这样方便？
主要是因为我们用了自己定义的低层过滤器(在@H@M服务类-方法或Servlet后执行的过滤器) BigFileDownload.class!!
 
```
package wx.web.filemanager;
import system.web.JWeb;
import system.web.filter.annotation.JWFilter;
import system.web.hm.annotation.H;
import system.web.hm.annotation.M;

@H("/filedow")
public class Dow {
    @M("/big/myfile")
    @JWFilter(configuration.filter.buttom.BigFileDownload.class)
    public static void doww(JWeb jw) {
        //真实项目中，我们都是从数据库取出文件的相对路径或由用户提交要下载的文件名
        //假设我们要下载的文件放在项目upload/img下.如图所示，我们就能输出文件流给用户了！
        jw.request.setAttribute("file", "upload/img/20170208_172948_4960000001Hydrangeas.jpg");
    }
}
```


现在，我们所有类似的下载，都可以使用此过滤器了。
再看看我们的过滤器定义：

 ```
package configuration.filter.buttom;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import system.web.JWeb;
import system.web.WebContext;
import system.web.filter.chain.FilterModel;

public class BigFileDownload extends FilterModel {

    {super.setLocation(LOCATION_BUTTOM); }
    public static final int buff = 1024 * 1024 * 3;
    @Override
    public void doFilter(JWeb jw) {
        Object attribute = jw.request.getAttribute("file");
        if (null != attribute) {
            String str = attribute.toString();
            jw.request.removeAttribute("file"); 
            String filename = str.substring(str.lastIndexOf("/")+1);
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(WebContext.getWebContext().WEB_PATH + str);
            } catch (FileNotFoundException ex) {
                jw.printOne("你访问的资源已经失效！");
                return;
            } 
            jw.response.reset();
            jw.response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            try {
                jw.response.setContentLength(fis.available());
            } catch (IOException ex) {
                jw.printOne("获得文件大小成错!");
                return;
            } 
            byte[] b = new byte[buff];
            int len;
            try {
                while ((len = fis.read(b)) > 0) {
                    jw.response.getOutputStream().write(b, 0, len);
                }
                jw.response.flushBuffer();
                jw.response.getOutputStream().close();
                fis.close();
            } catch (IOException e) {
            }
        }
    }
}
```


当然，我们可以发挥我们的想象力，定义统一的，与ui 框架统一的视图。比如说json…
(6)、使用扫描配置过滤器、过滤组！
之有我们都是使用@JWFilter来配置使用我们的过滤器、过滤组。现在，我们可以通过扫描来配置！
首先，我们要继承一个ConfigurationFilter类。
如果在实现configuration方法内，进行扫描配置，如下所示中的
lf.addFiltersByURL("/filedow/big/*", configuration.filter.buttom.BigFileDownload.class);
表示所有请求路径/filedow/big/开头的，都自动加入此过滤器！


```
package configuration.filter;
import system.web.filter.chain.config.ConfigurationFilter;
import system.web.filter.chain.config.LinkFilters;

public class MyConfigurationFilter extends ConfigurationFilter {

    @Override
    public void configuration(LinkFilters lf) {

//        //第一个参数[test.action.*]表示以此开头的(类+方法)路径，将绑定第二个参数所有的过滤器
//        lf.addFiltersByMethod("test.action.*", new Class[]{ 
//            //test.configuration.filter.f1.TwoFilter.class, Filters1.class, Filters8.class
//        });
//        第一个参数/x/ss表示以此开头的请求路径，将绑定第二个参数所有的过滤器
        lf.addFiltersByURL("/filedow/big/*", configuration.filter.buttom.BigFileDownload.class);
    }

}
```


再看先前的一例子，通过扫描配置，我们已经可以去除
```
@JWFilter(configuration.filter.buttom.BigFileDownload.class)的手工绑定了！！
 package wx.web.filemanager;

import system.web.JWeb;
import system.web.filter.annotation.JWFilter;
import system.web.hm.annotation.H;
import system.web.hm.annotation.M;

@H("/filedow")
public class Dow {
    @M("/big/myfile")
    public static void doww(JWeb jw) {
        //真实项目中，我们都是从数据库取出文件的相对路径或由用户提交要下载的文件名
        //假设我们要下载的文件放在项目upload/img下.如图所示，我们就能输出文件流给用户了！
        jw.request.setAttribute("file", "upload/img/20170208_172948_4960000001Hydrangeas.jpg");
    }
}
```



六、全局配置：WebConfigModel
我们看一下默认的配置：
基本上，我们HM_SUFFIX是重点。这是我们重点要讲的。
/**
     * 请求、响应的 字符编码
     */
    public String ENCODE = "UTF-8";

    public String CONTEXTPATH_KEY = "path_home";

    /**
     * 默认为空时，表示拦截所有的。
     * <p>
     * 如果要拦截指定后缀的请求，请以*.开头。如：*.jw
     */
    public String HM_SUFFIX = "";

    /**
     * 仅当属性HM_SUFFIX为空时，此属性才起作用。
     * <p>
     * 即：采用拦截所有的过滤方案时。
     */
    public boolean RESOURCE_MANAGER_OPEN = true;

    /**
     * SQL 值注入过滤（默认开，专对传参的值进行过滤）。如果要将sql语句发给sql执行，请将此开关设置成false
     */
    public boolean SQL_REJECT_OPEN = true;

    /**
     * 被转换的字符
     */
    public String SQL_REJECT_KEY = "'";

    /**
     * 转换后的字符
     */
    public String SQL_REJECT_VALUE = "&#39;";

    /**
     * 脚本注入过滤 因为html标签很多方括号，
     * <p>
     * 为了减少替换的性能，把方括号转换改成方括号+scr的转换
     */
    public String SCRIPT_REJECT_KEY = "<scr";

    public String SCRIPT_REJECT_VALUE = "&#60;scr";
    
    public String DATE_FORMAT = "yyyy-MM-dd";
    
public String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


下面我们来自定义配置。如，我们要修改HM_SUFFIX值。如下，表示我们只管理带.jw后缀的请求路径。
此种模式下，效率相对高些。
 ```
package configuration;

public class WebConfigModel extends system.web.config.temp.WebConfigModel {

    @Override
    public void config(system.web.config.temp.WebConfig config) {
        config.HM_SUFFIX = "*.jw";
    }

}
```
>>>>>>> origin/master
另外，我们发现还有时间、日期的配置。
这两个参数主要是在获得用户传参数时（有日期、或时间），如果没有指定日期、时间的格式，系统将自动采用默认的的公共配置来转化，如将 1988-09-01转化成日期。

后：
因为现在脚本、sql注入拦截，已经成了服务器安全软件的基本功，甚至一些jdbc封装的jar包又进行一次防注入过滤，
我还在想要不要在框架再实现一次，最后想想，还是留下用户自己选择吧。
当然，如果你们觉得还是很有必要，我会在后期加入的。
有问题的读者，请联系我pankeng1988w@163.com

