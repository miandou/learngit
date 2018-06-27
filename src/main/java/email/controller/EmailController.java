package email.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import email.bean.Email;
import email.bean.Email_File;
import email.bean.Sys_Parameter;
import email.dao.EmailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/8 0008.
 */
@Controller
@RequestMapping("/email")
public class EmailController
{
    @Autowired
    private EmailDao emailDao;

    @RequestMapping("/go")
    public String go()
    {

        return "index";
    }

    @RequestMapping("/save")
    public String save(Email email, Model model)
    {
        emailDao.insert(email);
        int id=email.getId();
        Email_File email_file=new Email_File();
        email_file.setEmail_id(id);
        email_file.setFile_name("fileName");
        email_file.setFile_path("filePath");

        emailDao.insertEmailFile(email_file);
        email_file.setFile_name("fileName2");
        email_file.setFile_path("filePath2");
        emailDao.insertEmailFile(email_file);
        model.addAttribute("message","success");
        return "index";
    }
}
