import java.lang.StringBuilder;

public class ViewportManager {
    public String overlay;
    private final char IGNORE='#';
    public ViewportManager(){
        this.overlay="";
    }
    public String format(String viewport) throws IllegalArgumentException{
        if(this.overlay.length()!=viewport.length()) throw new IllegalArgumentException("The viewport and overlay string lengths must match.");
        if(this.overlay.equals("")) return viewport;
        StringBuilder sb=new StringBuilder(viewport);
        for(int i=0;i<sb.length();i++){
            if(this.overlay.charAt(i)!=this.IGNORE){
                sb.setCharAt(i, this.overlay.charAt(i));
            }
        }
        return sb.toString();
    }
    public void setOverlay(String s){
        this.overlay=s;
    }
    public String getOverlay(){
        return this.overlay;
    }
}
