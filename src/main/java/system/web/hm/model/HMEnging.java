//package system.web.hm;
//
//import java.io.IOException;
//import system.web.XY;
//import system.web.JWeb;
//import java.lang.reflect.InvocationTargetException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.ServletException;
//import system.web.hm.model.LinkData;
//import system.web.hm.model.LinkModel;
//
///**
// *
// * @author wangchunzi
// */
//final public class HMEnging {
//
//    /**
//     *
//     * @param o
//     * @param jw
//     */
//    public final static void doHMEnging(JWeb jw, LinkModel o) {
//        
//        try {
//            int url_way;
//            String return_url;
//
//            //缓存第n次的处理结束跳转类型。万一第(n+1)执行资源为空，则采用第n次的视图跳转类型
//            url_way = o.SEND_URL_WAY;
////********************【第1次】开始********************
//            System.out.println(o.isStaticMethod+"//"+o.method.getName());
//            return_url = o.isStaticMethod 
//                    ? o.method.invoke(null, jw).toString() 
//                    : o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw)).toString();
//
//            //执行完后，检验是返回的路径是否有效路径。否则直接结束
//            System.out.println("执行完后，检验是返回的路径是否有效路径。否则直接结束:"+return_url);
//            if (null == return_url) {
//                return;
//            }
//
//            //假如返回路径不为空，进行获取第2次的执行资源
//            
//            o = LinkData.getLinkModel(return_url);
//System.out.println("进行获取第2次的执行资源:"+o);
//            //如果获取不到，则说明返回的路径是视图路径。
//            if (null == o) {
//                switch (url_way) {
//                    case XY.URL_FORWARD:
//                        jw.request.getRequestDispatcher(jw.container.ContextPath + return_url).forward(jw.request, jw.response);
//                        return;
//                    case XY.URL_REDIRECT: {
//                        jw.response.sendRedirect(jw.container.ContextPath + return_url);
//                        return;
//                    }
//                }
//            }
//
//            //缓存第n次的处理结束跳转类型。万一第(n+1)执行资源为空，则采用第n次的视图跳转类型
//            url_way = o.SEND_URL_WAY;
//
//            //如果获得得到，则说明进行了转处理。开始第2次的执行
////********************【第2次】开始。********************
//            return_url = o.isStaticMethod ? o.method.invoke(null, jw).toString() : o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw)).toString();
//
//            //执行完后，检验是返回的路径是否有效路径。否则直接结束
//            if (null == return_url) {
//                return;
//            }
//            //假如返回路径不为空，进行获取第2次的执行资源
//            o = LinkData.getLinkModel(return_url);
//
//            //如果获取不到，则说明返回的路径是视图路径。
//            if (null == o) {
//                switch (url_way) {
//                    case XY.URL_FORWARD:
//                        jw.request.getRequestDispatcher(jw.container.ContextPath + return_url).forward(jw.request, jw.response);
//                        return;
//                    case XY.URL_REDIRECT: {
//                        jw.response.sendRedirect(jw.container.ContextPath + return_url);
//                        return;
//                    }
//                }
//            }
//
//            //缓存第n次的处理结束跳转类型。万一第(n+1)执行资源为空，则采用第n次的视图跳转类型
//            url_way = o.SEND_URL_WAY;
//            //如果获得得到，则说明进行了转处理。开始第2次的执行
////********************【第3次】开始。********************
//            return_url = o.isStaticMethod ? o.method.invoke(null, jw).toString() : o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw)).toString();
//
//            //执行完后，检验是返回的路径是否有效路径。否则直接结束
//            if (null == return_url) {
//                return;
//            }
//            //假如返回路径不为空，进行获取第2次的执行资源
//            o = LinkData.getLinkModel(return_url);
//
//            //如果获取不到，则说明返回的路径是视图路径。
//            if (null == o) {
//                switch (url_way) {
//                    case XY.URL_FORWARD:
//                        jw.request.getRequestDispatcher(jw.container.ContextPath + return_url).forward(jw.request, jw.response);
//                        return;
//                    case XY.URL_REDIRECT: {
//                        jw.response.sendRedirect(jw.container.ContextPath + return_url);
//                        return;
//                    }
//                }
//            }
////********************【第4次】开始。********************
//            return_url = o.isStaticMethod ? o.method.invoke(null, jw).toString() : o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw)).toString();
//
//            //执行完后，检验是返回的路径是否有效路径。否则直接结束
//            if (null == return_url) {
//                return;
//            }
//            //假如返回路径不为空，进行获取第2次的执行资源
//            o = LinkData.getLinkModel(return_url);
//
//            //如果获取不到，则说明返回的路径是视图路径。
//            if (null == o) {
//                switch (url_way) {
//                    case XY.URL_FORWARD:
//                        jw.request.getRequestDispatcher(jw.container.ContextPath + return_url).forward(jw.request, jw.response);
//                        return;
//                    case XY.URL_REDIRECT: {
//                        jw.response.sendRedirect(jw.container.ContextPath + return_url);
//                        return;
//                    }
//                }
//            }
////********************【第5次】开始。********************
//            return_url = o.isStaticMethod ? o.method.invoke(null, jw).toString() : o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw)).toString();
//
//            //执行完后，检验是返回的路径是否有效路径。否则直接结束
//            if (null == return_url) {
//                return;
//            }
//            //假如返回路径不为空，进行获取第2次的执行资源
//            o = LinkData.getLinkModel(return_url);
//            s:
//            //如果获取不到，则说明返回的路径是视图路径。
//            if (null == o) {
//                switch (url_way) {
//                    case XY.URL_FORWARD:
//                        jw.request.getRequestDispatcher(jw.container.ContextPath + return_url).forward(jw.request, jw.response);
//                        return;
//                    case XY.URL_REDIRECT: {
//                        jw.response.sendRedirect(jw.container.ContextPath + return_url);
//                        return;
//                    }
//                }
//            }
//
//            //从第6次开始，进行5次循环。无论结束如果，强行结束！！
//            int wi = 0;
//            do {
//                //缓存第n次的处理结束跳转类型。万一第(n+1)执行资源为空，则采用第n次的视图跳转类型
//                url_way = o.SEND_URL_WAY;
////********************【第6++次】开始。********************
//                return_url = o.isStaticMethod 
//                        ? o.method.invoke(null, jw).toString() 
//                        : o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw)).toString();
//                
//                //执行完后，检验是返回的路径是否有效路径。否则直接结束
//                if (null == return_url) {
//                    return;
//                }
//                //假如返回路径不为空，进行获取第N次的执行资源
//                o = LinkData.getLinkModel(return_url);
//
//                //如果获取不到，则说明返回的路径是视图路径。
//                if (null == o) {
//                    switch (url_way) {
//                        case XY.URL_FORWARD:
//                            jw.request.getRequestDispatcher(jw.container.ContextPath + return_url).forward(jw.request, jw.response);
//                            return;
//                        case XY.URL_REDIRECT: {
//                            jw.response.sendRedirect(jw.container.ContextPath + return_url);
//                            return;
//                        }
//                    }
//                }
//            } while (++wi < 5);
//
//        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ServletException | IOException ex) {
//            Logger.getLogger(HMEnging.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//}
