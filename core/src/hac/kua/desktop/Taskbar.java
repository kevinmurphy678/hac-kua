package hac.kua.desktop;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;



public class Taskbar {
    private MenuBar menuBar;

    public void setupTaskbar(Stage stage)
    {
        menuBar = new MenuBar();

        final Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        menuBar.setMenuListener(new MenuBar.MenuBarListener() {
            @Override
            public void menuOpened (Menu menu) {
                System.out.println("Opened menu: " + menu.getTitle());
            }

            @Override
            public void menuClosed (Menu menu) {
                System.out.println("Closed menu: " + menu.getTitle());
            }
        });

        root.add(menuBar.getTable()).expandX().fill().row();
        root.add().expand().fill();

        Menu fileMenu = new Menu("A e s t h e t i c");
        fileMenu.addItem(new MenuItem("about", new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                Dialogs.showOKDialog(stage, "You are dead", "Why don't you go ahead and just kill yourself");
            }
        }));
        menuBar.addMenu(fileMenu);
    }

    public void addItem(Table item, String name)
    {
        final Table minimized = item;

        Menu bar = new Menu(name);
        bar.openButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                minimized.setVisible(true);
                bar.openButton.remove();
                menuBar.closeMenu();

                super.clicked(event, x, y);
            }
        });
        menuBar.addMenu(bar);
    }

}
