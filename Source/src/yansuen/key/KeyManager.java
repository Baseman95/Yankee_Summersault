package yansuen.key;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.function.BiConsumer;

/**
 *
 * @author Link162534
 */
public class KeyManager extends KeyAdapter {

    protected ArrayList<Integer> keyList = new ArrayList<>();
    protected ArrayList<KeyManagerListener> shortcutList = new ArrayList<>();

    @Override
    public void keyPressed(KeyEvent e) {
        Integer key = e.getKeyCode();
        if (keyList.contains(key))
            return;
        keyList.add(key);
        checkShortcuts(key);
    //    System.out.println("+" + key);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Integer key = e.getKeyCode();
        if (!keyList.contains(key))
            return;
        keyList.remove(key);
    //    System.out.println("-" + key);
    }

    public boolean isKeyPressed(Integer key) {
        return keyList.contains(key);
    }

    public void addShortcut(Object receiver, Integer key, BiConsumer<Integer, Object> event) {
        shortcutList.add(new KeyManagerListener(event, receiver, key));
    }

    public void removeShortcut(Object receiver, Integer key) {
        for (KeyManagerListener shortcut : shortcutList) {
            if (shortcut.event != receiver || !shortcut.key.equals(key)) continue;
            shortcutList.remove(shortcut);
            return;
        }
    }

    protected void checkShortcuts(Integer key) {
        Thread t = new Thread(() -> {
            for (KeyManagerListener shortcut : shortcutList) {
                if (shortcut.getKey().equals(key)) shortcut.execute();
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public static class KeyManagerListener {

        BiConsumer<Integer, Object> event;
        Object receiver;
        Integer key;

        public KeyManagerListener(BiConsumer<Integer, Object> event, Object source, Integer key) {
            this.event = event;
            this.receiver = source;
            this.key = key;
        }

        public Integer getKey() {
            return key;
        }

        public void execute() {
            event.accept(key, receiver);
        }

    }
}
