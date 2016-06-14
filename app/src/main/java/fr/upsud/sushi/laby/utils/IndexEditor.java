package fr.upsud.sushi.laby.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;

import fr.upsud.sushi.laby.maze.Level;

/**
 * Created by proval on 6/13/16.
 */
public class IndexEditor {

    private Level l;

    public IndexEditor(Level l) {

     //   ClassLoader loader = IndexEditor.class.getClassLoader();




        File f = new File("\\home\\proval\\AndroidStudioProjects\\Laby\\app\\src\\main\\assets\\blockly\\index.html");

        File f2 = new File("../.");

        File f3 = new File("/android_asset/blockly/index.html");



        if(f.exists() ) {
           System.out.println("FILE EXISTS");
        } else {
            System.out.println("FILE DOESNT EXIST");

        }
        if(f2.exists()) {
            System.out.println("FILE2 EXISTS");
        } else {
            System.out.println("FILE2 DOESNT EXIST");

        }
        if(f3.exists()) {
        System.out.println("FILE3 EXISTS");
        } else {
        System.out.println("FILE3 DOESNT EXIST");
        }

        //createHTML(f);

    }




    private void createHTML(File f){
        ArrayList<String> blocks = this.l.getAuthorizedBlocks();

        String content;
        content = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <title>Blockly Demo: Fixed Blockly</title>\n" +
                "  <script type=\"text/javascript\" src=\"blockly_compressed.js\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"blocks_compressed.js\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"javascript_compressed.js\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"msg/js/en.js\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"blocks_maze.js\"></script>\n" +
                "\n" +
                "  <style>\n" +
                "    body {\n" +
                "      background-color: #fff;\n" +
                "      font-family: sans-serif;\n" +
                "      padding:0;\n" +
                "      margin:0;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div id=\"blocklyDiv\" style=\"position:absolute;top:0;left:0;margin:0;padding:0;height: 100vh; width: 100vw;\"></div>\n" +
                "\n" +
                "  <xml id=\"toolbox\" style=\"display: none\">";

        for (String s : blocks) {
            content = content+"<block type=\"";
            content = content + s;
            content = content + "\"></block>\n";
        }

        content = content + "</xml>\n" +
                "\n" +
                "  <script>\n" +
                "    var workspace = Blockly.inject('blocklyDiv',\n" +
                "        {media: 'media/',\n" +
                "        zoom : { control: false,\n" +
                "                 wheel: false,\n" +
                "                 startScale : 0.90\n" +
                "                 },\n" +
                "         trashcan: true,\n" +
                "         toolbox: document.getElementById('toolbox')});\n" +
                "  </script>\n" +
                "\n" +
                "</body>\n" +
                "</html>";


        try {
            FileWriter fw = new FileWriter(f.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (Exception e){}

    }
}
