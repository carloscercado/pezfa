package com.pezfa.inventario.reports;

import com.pezfa.inventario.database.EspecieDB;
import com.pezfa.inventario.models.Especie;
import java.awt.Color;
import java.util.Calendar;
import java.util.List;
import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReporteConstructor
{

    public ReporteConstructor()
    {
        build();
    }

    private void build()
    {
        try
        {
            Calendar c = Calendar.getInstance();
            String dia = Integer.toString(c.get(Calendar.DATE));
            String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
            String annio = Integer.toString(c.get(Calendar.YEAR));

            StyleBuilder boldStyle = stl.style().bold();
            StyleBuilder boldCenteredStyle = stl.style(boldStyle)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            StyleBuilder sty = stl.style(boldCenteredStyle)
                    .setBorder(stl.pen1Point())
                    .setBackgroundColor(Color.LIGHT_GRAY);
            StyleBuilder titleStyle = stl.style(boldCenteredStyle)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setFontSize(15);

            report()
                    .setColumnTitleStyle(sty)
                    .highlightDetailEvenRows()
                    .columns(
                            col.column("Codigo", "codigo", type.stringType()).setWidth(50),
                            col.column("Nombre", "nombre", type.stringType()),
                            col.column("Tipo", "tipo", type.stringType()),
                            col.column("Cantidad", "cantidad", type.doubleType()).setValueFormatter(new ValueFormatter(" KG"))
                            .setWidth(80),
                            col.column("Precio", "precio", type.bigDecimalType()).setValueFormatter(new ValueFormatter(" BS"))
                            .setWidth(80))
                    .title(cmp.text("Reporte de Especies Generales  " + dia + "/" + mes + "/" + annio).setStyle(boldCenteredStyle)
                    .add(
                            cmp.image(getClass().getResourceAsStream("")).setFixedDimension(80, 80),
                            cmp.text("DynamicReports").setStyle(titleStyle).setHorizontalAlignment(HorizontalAlignment.LEFT),
                            cmp.text("Getting started").setStyle(titleStyle).setHorizontalAlignment(HorizontalAlignment.RIGHT))
                    .newRow()
                    .add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10)) 
                    .pageFooter(cmp.pageXofY().setStyle(boldCenteredStyle))
                    .setDataSource(createDataSource()))
                    .show();
        } catch (DRException e)
        {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource()
    {
        EspecieDB db = new EspecieDB();
        List<Especie> data = db.read("from Especie esp where esp.cantidad>0");
        return new JRBeanCollectionDataSource(data);
    }

    public static void main(String[] args)
    {
        new ReporteConstructor();
    }

    private static class ValueFormatter extends AbstractValueFormatter<String, Number>
    {

        private static final long serialVersionUID = 1L;
        private String a;

        public ValueFormatter(String a)
        {
            this.a = a;
        }

        @Override
        public String format(Number value, ReportParameters reportParameters)
        {
            return type.bigDecimalType().valueToString(value, reportParameters.getLocale()) + a;
        }
    }
}
