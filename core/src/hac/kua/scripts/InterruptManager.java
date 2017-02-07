package hac.kua.scripts;

import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.DebugLib;

/**
 * Created by kevin on 2/6/2017.
 * Allows lua scripts to be terminated
 * http://stackoverflow.com/questions/17496868/lua-java-luaj-handling-or-interrupting-infinite-loops-and-threads
 */
public class InterruptManager extends DebugLib {

    public boolean isInterrupted() {
        return interrupted;
    }

    public void setInterrupted(boolean interrupted) {
        this.interrupted = interrupted;
    }

    private boolean interrupted = false;

    @Override
    public void onInstruction(int pc, Varargs v, int top) {
        if (interrupted) {
            throw new ScriptInterruptException();
        }
        super.onInstruction(pc, v, top);
    }
    public static class ScriptInterruptException extends RuntimeException {}
}
