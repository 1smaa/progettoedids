package com.viewport;

public class ViewportManager {
    public String overlay;
    private final char IGNORE='#';
    public ViewportManager(){
        this.overlay="";
    }
    public String format(String viewport) throws IllegalArgumentException{
        //if(this.overlay.length()<viewport.length()) throw new IllegalArgumentException("The viewport and overlay string lengths must match.");
        if(this.overlay.equals("")) return viewport;
        String[] overlayLines=this.overlay.split("\n");
        String[] viewportLines=viewport.split("\n");
        String res=overlayLines[0]+"\n";
        for(int i=1;i<overlayLines.length;i++){
            if(i>viewportLines.length) {
                res+=overlayLines[i]+"\n";
                continue;
            }
            StringBuilder bd=new StringBuilder(overlayLines[i]);
            for(int j=0;j<bd.length();j++){
                if(j<viewportLines[i-1].length()) bd.setCharAt(j+1,viewportLines[i-1].charAt(j));
            }
            res+=bd+"\n";
            //System.out.println(res);
        }
        return res;
    }
    public void setOverlay(String s){
        this.overlay=s;
    }
    public String getOverlay(){
        return this.overlay;
    }
}
