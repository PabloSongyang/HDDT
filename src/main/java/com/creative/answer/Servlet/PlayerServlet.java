package com.creative.answer.Servlet;

import com.creative.answer.common.CommonData;
import com.creative.answer.config.ServletConfig;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet({
        "/ajax/ajax_set_msg",
        "/ajax/ajax_get_msg"
})
public class PlayerServlet extends HttpServlet {
    private Gson gson;
    private String str;
    private Map<String, Object> map;

    @Override
    public void init() {
        gson = new Gson();
        map = new HashMap<>();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String AJAX = ServletConfig.getAJAX(request);
        switch (AJAX) {
            case "ajax/ajax_set_msg":
                // 创建上传所需要的两个对象
                DiskFileItemFactory factory = new DiskFileItemFactory();  // 磁盘文件对象
                ServletFileUpload sfu = new ServletFileUpload(factory);   // 文件上传对象

                // 设置解析文件上传中的文件名的编码格式
                sfu.setHeaderEncoding("utf-8");

                // 将表单中的所有数据信息放入 list容器中
                try {
                    // 创建 list容器用来保存 表单中的所有数据信息
                    List<FileItem> items = sfu.parseRequest(request);
                    Map<String, Object> tempMap = new HashMap<>();

                    // 遍历 list容器，处理 每个数据项 中的信息
                    for (FileItem item : items) {
                        // 判断是否是普通项
                        if (item.isFormField()) {
                            // 处理 普通数据项 信息
                            tempMap.put(item.getFieldName(), new String(item.getString().getBytes("iso-8859-1"), "utf-8"));
                            System.out.println(item.getFieldName() + "  " + new String(item.getString().getBytes("iso-8859-1"), "utf-8"));
                        } else {
                            // 处理 文件数据项 信息
                            String originalName = item.getName();
                            String newName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(System.currentTimeMillis()) + originalName.substring(originalName.lastIndexOf("."));
                            String path = getServletContext().getRealPath("/upload");

                            if (!new File(path).exists())
                                new File(path).mkdirs();

                            File file = new File(path, newName);
                            InputStream is = item.getInputStream();
                            FileOutputStream fos = new FileOutputStream(file);

                            byte[] b = new byte[1024 * 8];

                            int len;

                            while ((len = is.read(b)) != -1) {
                                fos.write(b, 0, len);
                                fos.flush();
                            }
                            fos.close();
                            is.close();

                            tempMap.put("photo", "/upload/" + newName);
                        }
                    }

                    map.put("nikeName", tempMap.get("nikeName").toString());
                    map.put("photo", tempMap.get("photo").toString());
                    CommonData.hall_status = Integer.parseInt(tempMap.get("code").toString());
                    map.put("code", CommonData.hall_status);

                    if (tempMap.size() > 0)
                        map.put("code", 1);
                    else
                        map.put("code", 0);

                } catch (FileUploadException e) {
                    e.printStackTrace();
                }


                System.out.println(map);
                out.print(gson.toJson(map));
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String AJAX = ServletConfig.getAJAX(request);
        switch (AJAX) {
            case "ajax/ajax_get_msg":
                out.print(gson.toJson(map));
                System.out.println("map " + gson.toJson(map));
                break;

        }
    }
}
