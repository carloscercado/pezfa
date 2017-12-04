package com.pezfa.inventario.reporte;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.faces.context.FacesContext;
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
public class reporte extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String nombre = request.getParameter("nombre");
            ServletOutputStream out = response.getOutputStream();
            JasperReport jp;
            if (nombre.equals("inventario"))
            {
                response.setContentType("application/pdf");
                File re = new File(this.getServletContext().getRealPath("WEB-INF/reportes/inventario.jasper"));
                jp = (JasperReport) JRLoader.loadObject(re);
                Connection con = Conexion.getConection();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jp, null, con);
                JRExporter exporter = new JRPdfExporter();

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporter.exportReport();

            } else if (nombre.equals("compra"))
            {
                response.setContentType("application/pdf");
                File re = new File(this.getServletContext().getRealPath("WEB-INF/reportes/compra.jasper"));
                jp = (JasperReport) JRLoader.loadObject(re);
                Connection con = Conexion.getConection();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jp, null, con);
                JRExporter exporter = new JRPdfExporter();

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporter.exportReport();

            } else if (nombre.equals("venta"))
            {
                response.setContentType("application/pdf");

                File re = new File(this.getServletContext().getRealPath("WEB-INF/reportes/venta.jasper"));

                HashMap map = new HashMap();
                String fecha = request.getParameter("fecha"); // tomas el parametro enviado desde la vista
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); //colocas el formato en que viene
                java.util.Date date = formatter.parse(fecha); // haces que el string con la fecha se convierta en un DATE usando el formato correcto
                map.put("fecha", date); // seteas el parametro para jasperreport

                jp = (JasperReport) JRLoader.loadObject(re);
                Connection con = Conexion.getConection();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jp, map, con); //aqui pasas el jasperreport, los parrametros y la conexion
                JRExporter exporter = new JRPdfExporter();

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporter.exportReport();

            } else if (nombre.equals("proveedor"))
            {
                response.setContentType("application/pdf");
                File re = new File(this.getServletContext().getRealPath("WEB-INF/reportes/proveedor.jasper"));
                jp = (JasperReport) JRLoader.loadObject(re);
                Connection con = Conexion.getConection();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jp, null, con);
                JRExporter exporter = new JRPdfExporter();

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporter.exportReport();

            } else if (nombre.equals("cliente"))
            {
                response.setContentType("application/pdf");
                File re = new File(this.getServletContext().getRealPath("WEB-INF/reportes/cliente.jasper"));
                jp = (JasperReport) JRLoader.loadObject(re);
                Connection con = Conexion.getConection();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jp, null, con);
                JRExporter exporter = new JRPdfExporter();

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporter.exportReport();

            } 

        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
