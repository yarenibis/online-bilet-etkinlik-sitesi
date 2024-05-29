/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestSQLInjection;

import dao.KullanıcıDAO;

public class LoginInjection {
    public static void main(String[] args) {
         KullanıcıDAO kullanıcıDAO = new KullanıcıDAO();

        // normal giriş denemesi
      
//            String testEmail1 = "ahmet@gmail.com";
//            String testPassword1 = "000";
//            boolean result1 = kullanıcıDAO.kullanıcıGirişi(testEmail1, testPassword1);
//            System.out.println(result1);
//            
//        //Injection örnekleri    
//            String testEmail2 = "ahmet@gmail.com";
//            String testPassword2 = "' OR '1'='1' --";
//            boolean result2 = kullanıcıDAO.kullanıcıGirişi(testEmail2, testPassword2);
//            System.out.println(result2);
//            
//            String testEmail3 = "ahmet@gmail.com";
//            String testPassword3 = "' OR '1'='1' /*";
//            boolean result3 = kullanıcıDAO.kullanıcıGirişi(testEmail3, testPassword3);
//            System.out.println(result3);

       
        
    }
}
