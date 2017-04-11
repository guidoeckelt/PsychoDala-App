package graphic;

import graphic.defaultt.FillRectTask;
import graphic.defaultt.SetFillTask;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Affine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guido on 09.04.2017.
 */
public class GraphicCanvas {

    private double width;
    private double height;
    private Canvas canvas;
    private GraphicsContext gc;
    private List<GraphicTask> graphicTasks;

    public GraphicCanvas() {
        this(100,100);
    }

    public GraphicCanvas(double width, double height) {
        this.width = width;
        this.height = height;
        this.canvas = new Canvas(width,height);
        this.gc = this.canvas.getGraphicsContext2D();
        this.graphicTasks = new ArrayList<>();
    }


    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public List<GraphicTask> getGraphicTasks() {
        return graphicTasks;
    }

    public boolean isUsed() {
        return this.graphicTasks.size() > 0;
    }

    private void addGraphicTask(GraphicTask graphicTask) {
        this.graphicTasks.add(graphicTask);
    }

    public void save() {
        this.gc.save();
    }

    public void restore() {
        this.gc.restore();
    }

    public void translate(double x, double y) {
        this.gc.translate(x, y);
    }

    public void scale(double x, double y) {
        this.gc.scale(x, y);
    }

    public void rotate(double degrees) {
        this.gc.rotate(degrees);
    }

    public void transform(double mxx, double myx, double mxy, double myy, double mxt, double myt) {
        this.gc.transform(mxx, myx, mxy, myy, mxt, myt);
    }

    public void transform(Affine xform) {
        this.gc.transform(xform);
    }

    public void setTransform(double mxx, double myx, double mxy, double myy, double mxt, double myt) {
        this.gc.setTransform(mxx, myx, mxy, myy, mxt, myt);
    }

    public Affine getTransform(Affine xform) {
        return this.gc.getTransform(xform);
    }

    public Affine getTransform() {
        return this.gc.getTransform();
    }

    public void setTransform(Affine xform) {
        this.gc.setTransform(xform);
    }

    public double getGlobalAlpha() {
        return this.gc.getGlobalAlpha();
    }

    public void setGlobalAlpha(double alpha) {
        this.gc.setGlobalAlpha(alpha);
    }

    public BlendMode getGlobalBlendMode() {
        return this.gc.getGlobalBlendMode();
    }

    public void setGlobalBlendMode(BlendMode op) {
        this.gc.setGlobalBlendMode(op);
    }

    public Paint getFill() {
        return this.gc.getFill();
    }

    public void setFill(Paint fill) {
        this.addGraphicTask(new SetFillTask(fill));
    }

    public Paint getStroke() {
        return this.gc.getStroke();
    }

    public void setStroke(Paint p) {
        this.gc.setStroke(p);
    }

    public double getLineWidth() {
        return this.gc.getLineWidth();
    }

    public void setLineWidth(double lw) {
        this.gc.setLineWidth(lw);
    }

    public StrokeLineCap getLineCap() {
        return this.gc.getLineCap();
    }

    public void setLineCap(StrokeLineCap cap) {
        this.gc.setLineCap(cap);
    }

    public StrokeLineJoin getLineJoin() {
        return this.gc.getLineJoin();
    }

    public void setLineJoin(StrokeLineJoin join) {
        this.gc.setLineJoin(join);
    }

    public double getMiterLimit() {
        return this.gc.getMiterLimit();
    }

    public void setMiterLimit(double ml) {
        this.gc.setMiterLimit(ml);
    }

    public double[] getLineDashes() {
        return this.gc.getLineDashes();
    }

    public void setLineDashes(double... dashes) {
        this.gc.setLineDashes(dashes);
    }

    public double getLineDashOffset() {
        return this.gc.getLineDashOffset();
    }

    public void setLineDashOffset(double dashOffset) {
        this.gc.setLineDashOffset(dashOffset);
    }

    public Font getFont() {
        return this.gc.getFont();
    }

    public void setFont(Font f) {
        this.gc.setFont(f);
    }

    public FontSmoothingType getFontSmoothingType() {
        return this.gc.getFontSmoothingType();
    }

    public void setFontSmoothingType(FontSmoothingType fontsmoothing) {
        this.gc.setFontSmoothingType(fontsmoothing);
    }

    public TextAlignment getTextAlign() {
        return this.gc.getTextAlign();
    }

    public void setTextAlign(TextAlignment align) {
        this.gc.setTextAlign(align);
    }

    public VPos getTextBaseline() {
        return this.gc.getTextBaseline();
    }

    public void setTextBaseline(VPos baseline) {
        this.gc.setTextBaseline(baseline);
    }

    public void fillText(String text, double x, double y) {
        this.gc.fillText(text, x, y);
    }

    public void strokeText(String text, double x, double y) {
        this.gc.strokeText(text, x, y);
    }

    public void fillText(String text, double x, double y, double maxWidth) {
        this.gc.fillText(text, x, y, maxWidth);
    }

    public void strokeText(String text, double x, double y, double maxWidth) {
        this.gc.strokeText(text, x, y, maxWidth);
    }

    public FillRule getFillRule() {
        return this.gc.getFillRule();
    }

    public void setFillRule(FillRule fillRule) {
        this.gc.setFillRule(fillRule);
    }

    public void beginPath() {
        this.gc.beginPath();
    }

    public void moveTo(double x0, double y0) {
        this.gc.moveTo(x0, y0);
    }

    public void lineTo(double x1, double y1) {
        this.gc.lineTo(x1, y1);
    }

    public void quadraticCurveTo(double xc, double yc, double x1, double y1) {
        this.gc.quadraticCurveTo(xc, yc, x1, y1);
    }

    public void bezierCurveTo(double xc1, double yc1, double xc2, double yc2, double x1, double y1) {
        this.gc.bezierCurveTo(xc1, yc1, xc2, yc2, x1, y1);
    }

    public void arcTo(double x1, double y1, double x2, double y2, double radius) {
        this.gc.arcTo(x1, y1, x2, y2, radius);
    }

    public void arc(double centerX, double centerY, double radiusX, double radiusY, double startAngle, double length) {
        this.gc.arc(centerX, centerY, radiusX, radiusY, startAngle, length);
    }

    public void rect(double x, double y, double w, double h) {
        this.gc.rect(x, y, w, h);
    }

    public void appendSVGPath(String svgpath) {
        this.gc.appendSVGPath(svgpath);
    }

    public void closePath() {
        this.gc.closePath();
    }

    public void fill() {
        this.gc.fill();
    }

    public void stroke() {
        this.gc.stroke();
    }

    public void clip() {
        this.gc.clip();
    }

    public boolean isPointInPath(double x, double y) {
        return this.gc.isPointInPath(x, y);
    }

    public void clearRect(double x, double y, double w, double h) {
        this.gc.clearRect(x, y, w, h);
    }

    public void fillRect(double x, double y, double width, double height) {
        this.addGraphicTask(new FillRectTask(x, y, width, height));
    }

    public void strokeRect(double x, double y, double w, double h) {
        this.gc.strokeRect(x, y, w, h);
    }

    public void fillOval(double x, double y, double w, double h) {
        this.gc.fillOval(x, y, w, h);
    }

    public void strokeOval(double x, double y, double w, double h) {
        this.gc.strokeOval(x, y, w, h);
    }

    public void fillArc(double x, double y, double w, double h, double startAngle, double arcExtent, ArcType closure) {
        this.gc.fillArc(x, y, w, h, startAngle, arcExtent, closure);
    }

    public void strokeArc(double x, double y, double w, double h, double startAngle, double arcExtent, ArcType closure) {
        this.gc.strokeArc(x, y, w, h, startAngle, arcExtent, closure);
    }

    public void fillRoundRect(double x, double y, double w, double h, double arcWidth, double arcHeight) {
        this.gc.fillRoundRect(x, y, w, h, arcWidth, arcHeight);
    }

    public void strokeRoundRect(double x, double y, double w, double h, double arcWidth, double arcHeight) {
        this.gc.strokeRoundRect(x, y, w, h, arcWidth, arcHeight);
    }

    public void strokeLine(double x1, double y1, double x2, double y2) {
        this.gc.strokeLine(x1, y1, x2, y2);
    }

    public void fillPolygon(double[] xPoints, double[] yPoints, int nPoints) {
        this.gc.fillPolygon(xPoints, yPoints, nPoints);
    }

    public void strokePolygon(double[] xPoints, double[] yPoints, int nPoints) {
        this.gc.strokePolygon(xPoints, yPoints, nPoints);
    }

    public void strokePolyline(double[] xPoints, double[] yPoints, int nPoints) {
        this.gc.strokePolyline(xPoints, yPoints, nPoints);
    }

    public void drawImage(Image img, double x, double y) {
        this.gc.drawImage(img, x, y);
    }

    public void drawImage(Image img, double x, double y, double w, double h) {
        this.gc.drawImage(img, x, y, w, h);
    }

    public void drawImage(Image img, double sx, double sy, double sw, double sh, double dx, double dy, double dw, double dh) {
        this.gc.drawImage(img, sx, sy, sw, sh, dx, dy, dw, dh);
    }

    public PixelWriter getPixelWriter() {
        return this.gc.getPixelWriter();
    }

    public void setEffect(Effect e) {
        this.gc.setEffect(e);
    }

    public Effect getEffect(Effect e) {
        return this.gc.getEffect(e);
    }

    public void applyEffect(Effect e) {
        this.gc.applyEffect(e);
    }

}
