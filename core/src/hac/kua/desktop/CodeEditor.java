package hac.kua.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.util.highlight.Highlighter;
import com.kotcrab.vis.ui.widget.*;
import hac.kua.hackable.Hackable;
import hac.kua.hackable.Hackable_Manager;
import hac.kua.utils.Core;

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
        if(hackable.script.scriptContents == null)
            this.originalScript = Gdx.files.internal(hackable.script.scriptFileName).readString();
        else
            this.originalScript = hackable.script.scriptContents;

        this.originalScript =  this.originalScript.replace("\r", "");
        setupEditor(Core.stage);
    }


    //Compile Button
    private void compile()
    {
        //If our script was interrupted / stopped, allow it to run again
        interrupt();

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hackable.script.interrupted=false;
        hackable.script.interruptManager.setInterrupted(false);

        if(hackable.script.loadString(codeEditor.getText()))
        {
            statusLabel.setColor(Color.LIME);
            statusLabel.setText("Status: Running");
        }
        else
        {
            statusLabel.setColor(Color.ORANGE);
            statusLabel.setText("Status: Compile failed");
            hackable.script.interruptManager.setInterrupted(true);
        }

    }

    //Reset to default
    private void reset()
    {
        codeEditor.setText(this.originalScript);
    }


    //Force interrupt the script in-case user accidently creates infinite loop
    private void interrupt() {
        statusLabel.setColor(Color.RED);
        statusLabel.setText("Status: Stopped");
        hackable.script.forceInterruption();
    }

    private void addToolTip(Actor widget, String text)
    {
        new Tooltip.Builder(text).target(widget).build();
    }


    private VisLabel statusLabel;
    private void setupEditor(Stage stage)
    {
        codeEditor = new HighlightTextArea("Code");

        codeWindow = new VisWindow(Hackable_Manager.hackables.findKey(hackable,false)) {
            @Override
            public void act(float delta) {
                if(hackable.script.interruptManager.isInterrupted() || hackable.script.interrupted)
                {
                    statusLabel.setColor(Color.RED);
                    statusLabel.setText("Status: Stopped");
                }
            }
        };

       // codeWindow.addCloseButton();


        Label titleLabel = codeWindow.getTitleLabel();
        Table titleTable = codeWindow.getTitleTable();

        VisImageButton minimizeButton = new VisImageButton("blue");
        titleTable.add(minimizeButton);
        minimizeButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                codeWindow.setVisible(false);
                Core.taskbar.addItem(codeWindow, codeWindow.getTitleLabel().getText().toString());
            }
        });


        VisImageButton closeButton = new VisImageButton("close");
        titleTable.add(closeButton);
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                codeWindow.remove();
            }
        });

        if (titleLabel.getLabelAlign() == Align.center && titleTable.getChildren().size == 2)
            titleTable.getCell(titleLabel).padLeft(closeButton.getWidth() * 2);

        codeWindow.setSize(600,832);
        codeWindow.setPosition(977,41);
        codeWindow.setResizable(true);

        TableUtils.setSpacingDefaults(codeWindow);

        codeWindow.add(codeEditor.createCompatibleScrollPane()).grow().row();
        VisTable buttonsTable = new VisTable(true);

        //Compile Button
        VisTextButton buttonCompile = new VisTextButton("Compile");
        addToolTip(buttonCompile, "Compiles and starts the script");
        buttonCompile.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               compile();
            }
        });
        buttonsTable.add(buttonCompile);

        //Reset Button
        VisTextButton buttonReset = new VisTextButton("Reset");
        addToolTip(buttonReset, "Resets the script to its original state");
        buttonReset.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                reset();
            }
        });
        buttonsTable.add(buttonReset);


        //Interrupt Button
        VisTextButton buttonInterrupt = new VisTextButton("Stop");
        addToolTip(buttonInterrupt, "Stops / Interrupts the script");
        buttonInterrupt.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                interrupt();
            }
        });
        buttonsTable.add(buttonInterrupt);

        statusLabel = new VisLabel();
        statusLabel.setColor(Color.LIME);
        statusLabel.setText("Status: Running");
        buttonsTable.add(statusLabel);

        //Add buttons
        codeWindow.add(buttonsTable).align(Align.left).padLeft(4).padBottom(4);

        //Set Text
        String text = Gdx.files.internal("assets/lua/hello.lua").readString();
        text = text.replace("\r", "");
        codeEditor.setText(text);
        codeEditor.setFocusTraversal(false);
        codeEditor.addListener(new InputListener(){

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
//                if(keycode == 66)
//                {
//                    String[] lines = codeEditor.getText().split("\n");
//
//                    if(lines.length > codeEditor.getCursorLine()) {
//                        if (lines[codeEditor.getCursorLine()].startsWith("    ")) {
//                            lines[codeEditor.getCursorLine()+1] = "    " + lines[codeEditor.getCursorLine()+1]; //Add 4 spaces to start
//                            final StringBuilder builder = new StringBuilder();
//                            for(String line : lines) builder.append(line + "\n");
//                            codeEditor.setText(builder.toString());
//                        }
//                    }
//
//                }
               return super.keyDown(event, keycode);
            }

            @Override
           public boolean keyTyped(InputEvent event, char character) {
//                if(character == '\t')
//                {
//                    String[] lines = codeEditor.getText().split("\n");
//
//                    if(lines.length > codeEditor.getCursorLine()) {
//                        lines[codeEditor.getCursorLine()]
//                        final StringBuilder builder = new StringBuilder();
//                        for(String line : lines) builder.append(line + "\n");
//                        codeEditor.setText(builder.toString());
//
//                    }
//                }

               return super.keyTyped(event, character);
           }
         });


        //Highlighting
        Highlighter highlighter = new Highlighter();
        //it is much more reliable to use regex for keyword detection
        highlighter.regex(Color.valueOf("00FFFF"), "\\b(and|end|in|repeat|break|false|local|return|do|for|nil|then|else|function|not|true|elseif|if|or|until|while)\\b");
        highlighter.regex(Color.PINK, "\"(.*?)\"");
        highlighter.regex(Color.valueOf("FF00FF"), "(?=--)(.*)(?=[\\r\\n])\n");
        codeEditor.setHighlighter(highlighter);
        stage.addActor(codeWindow);
    }
}
