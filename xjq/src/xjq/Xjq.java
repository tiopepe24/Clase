/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xjq;

import javax.xml.namespace.QName;
import javax.xml.xquery.*;
import net.xqj.basex.BaseXXQDataSource;

/**
 *
 * @author dvd
 */
public class Xjq {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws XQException
    {
        XQDataSource xqs = new BaseXXQDataSource();
        xqs.setProperty("serverName", "172.16.6.98");
        xqs.setProperty("port", "1984");
        xqs.setProperty("user", "admin");
        xqs.setProperty("password", "admin");
        xqs.setProperty("databaseName", "BaseX_BD");
        // Change USERNAME and PASSWORD values
        XQConnection conn = xqs.getConnection();

        XQPreparedExpression xqpe = conn.prepareExpression("//*");

        XQResultSequence rs = xqpe.executeQuery();

        while(rs.next())
            System.out.println(rs.getItemAsString(null));

        conn.close();
    }
    
}
