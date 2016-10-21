package com.lrm.biz;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFReport {

	public static void main(String[] args) {
		try {
			// 1.创建一个document
			Document document = new Document();

			// 2.定义pdfWriter，指明文件输出流输出到一个文件
			PdfWriter.getInstance(document, new FileOutputStream("D:\\test.pdf"));

			// 3.打开文档
			document.open();

			// 字体
			Font font = new Font();
			font.setFamily("STSongStd-Light");
			// 颜色
			font.setColor(BaseColor.BLUE);
			// 4.添加内容
			Paragraph content = new Paragraph("xxx!", font);

			document.add(content);

			// 添加段落
			for (int i = 0; i < 100; i++) {
				document.add(new Paragraph("HelloWorld" + "," + "Hello iText" + "," + "HelloxDuan"));
			}

			// 5.关闭
			document.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
