package hac.kua.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.util.highlight.Highlighter;
import com.kotcrab.vis.ui.widget.HighlightTextArea;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;
import hac.kua.hackable.Hackable;
import hac.kua.hackable.Hackable_Manager;

/**
 * Created by kevin on 1/23/2017.
 * Code editor for editing lua scripts of Hackable objects
 */
public class CodeEditor {


    //The hackable currently being edited
    private Hackable hackable;

    private String originalScript;
    private VisWindow codeWindow;
    private HighlightTextArea codeEditor;

    public CodeEditor(Hackable hackable)
    {
        this.hackable = hackable;
        this.originalScript = Gdx.files.internal(hackable.script.scriptFileName).readString();
        setupEditor(Core.stage);
    }

    //Compile Button
    private void Compile()
    {
        hackable.script.loadString(codeEditor.getText());
    }

    //Reset to default
    private void Reset()
    {
        codeEditor.setText(this.originalScript);
    }

    private void hide(Stage stage)
    {

    }

    private void setupEditor(Stage stage)
    {
        VisUI.load();

        codeEditor = new HighlightTextArea("Code");
        stage.addActor(codeEditor);

        codeWindow = new VisWindow(Hackable_Manager.hackables.findKey(hackable,false));
        codeWindow.setSize(600,832);
        codeWindow.setPosition(977,41);
        codeWindow.setResizable(true);
        TableUtils.setSpacingDefaults(codeWindow);


        codeWindow.add(codeEditor.createCompatibleScrollPane()).grow().colspan(20).row();
        VisTable buttonsTable = new VisTable(true);


        //Compile Button
        VisTextButton buttonCompile = new VisTextButton("Compile");
        buttonCompile.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               Compile();
            }
        });
        buttonsTable.add(buttonCompile);


        //Reset Button
        VisTextButton buttonReset = new VisTextButton("Reset");
        buttonReset.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Reset();
            }
        });
        buttonsTable.add(buttonReset);

        //Add buttons
        codeWindow.add(buttonsTable).align(Align.left).padLeft(4).padBottom(4);

        //Set Text
        String text = Gdx.files.internal("assets/lua/hello.lua").readString();
        codeEditor.setText(text);

        //Highlighting
        Highlighter highlighter = new Highlighter();
        //it is much more reliable to use regex for keyword detection
        highlighter.regex(Color.valueOf("66CCB3"), "\\b(and|end|in|repeat|break|false|local|return|do|for|nil|then|else|function|not|true|elseif|if|or|until|while)\\b");
        highlighter.regex(Color.valueOf("BED6FF"), "\\b(int|float|boolean|short|long|char)\\b");
        highlighter.regex(Color.valueOf("75715E"), "/\\*(?:.|[\\n\\r])*?\\*/"); //block comments (/* comment */)
        codeEditor.setHighlighter(highlighter);

        stage.addActor(codeWindow);
    }
}
