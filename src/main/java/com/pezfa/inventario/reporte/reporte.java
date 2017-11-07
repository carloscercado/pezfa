
package com.pezfa.inventario.reporte;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;


/**
 *
 * @author Romario
 */
public class reporte extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            String nombre = request.getParameter("nombre");
            ServletOutputStream out = response.getOutputStream();
            JasperReport jp;
            if (nombre.equals("inventario")) {
                response.setContentType("application/pdf");
                File re = new File(this.getServletContext().getRealPath("WEB-INF/reportes/inventario.jasper"));
                jp = (JasperReport) JRLoader.loadObject(re);
                Connection con = Conexion.getConection();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jp, null, con);
                JRExporter exporter = new JRPdfExporter();

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporter.exportReport();

            } else if (nombre.equals("compra")) {
                response.setContentType("application/pdf");
                File re = new File(this.getServletContext().getRealPath("WEB-INF/reportes/compra.jasper"));
                jp = (JasperReport) JRLoader.loadObject(re);
                Connection con = Conexion.getConection();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jp, null, con);
                JRExporter exporter = new JRPdfExporter();

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporter.exportReport();
                
            } else if (nombre.equals("venta")) {
                response.setContentType("application/pdf");
                File re = new File(this.getServletContext().getRealPath("WEB-INF/reportes/venta.jasper"));
                jp = (JasperReport) JRLoader.loadObject(re);
                Connection con = Conexion.getConection();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jp, null, con);
                JRExporter exporter = new JRPdfExporter();

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporter.exportReport();
            
            } else if (nombre.equals("proveedor")) {
                response.setContentType("application/pdf");
                File re = new File(this.getServletContext().getRealPath("WEB-INF/reportes/proveedor.jasper"));
                jp = (JasperReport) JRLoader.loadObject(re);
                Connection con = Conexion.getConection();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jp, null, con);
                JRExporter exporter = new JRPdfExporter();

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporter.exportReport();
           
            } else if (nombre.equals("cliente")) {
                response.setContentType("application/pdf");
                File re = new File(this.getServletContext().getRealPath("WEB-INF/reportes/cliente.jasper"));
                jp = (JasperReport) JRLoader.loadObject(re);
                Connection con = Conexion.getConection();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jp, null, con);
                JRExporter exporter = new JRPdfExporter();

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporter.exportReport();
            
            }  else if (nombre.equals("ven_1")) {
                response.setContentType("application/pdf");
                File re = new File(this.getServletContext().getRealPath("WEB-INF/reportes/ven_1.jasper"));
                jp = (JasperReport) JRLoader.loadObject(re);
                Connection con = Conexion.getConection();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jp, null, con);
                JRExporter exporter = new JRPdfExporter();

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporter.exportReport();
                
            } else if (nombre.equals("ven_2")) {
                response.setContentType("application/pdf");
                File re = new File(this.getServletContext().getRealPath("WEB-INF/reportes/ven_2.jasper"));
                jp = (JasperReport) JRLoader.loadObject(re);
                Connection con = Conexion.getConection();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jp, null, con);
                JRExporter exporter = new JRPdfExporter();

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporter.exportReport();
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
