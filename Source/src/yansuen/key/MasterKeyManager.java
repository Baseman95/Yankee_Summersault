package yansuen.key;

import java.util.HashMap;
import yansuen.network.Network;

/**
 *
 * @author Link162534
 */
public class MasterKeyManager {

    protected Network network;
    protected LocalKeyManager localManager = new LocalKeyManager();
    protected HashMap<Integer, NetworkKeyManager> networkManagerList = new HashMap<>();

    public MasterKeyManager() {
        this.network = null;
    }

    public MasterKeyManager(Network network) {
        this.network = network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public boolean isKeyPressed(Integer key) {
        return localManager.isKeyPressed(key);
    }

    public boolean isKeyPressed(Integer key, int id) {
        if (id == -1 || id == network.getId())
            return isKeyPressed(key);
        NetworkKeyManager knm = networkManagerList.get(id);
        if (knm == null) {
            networkManagerList.put(id, new NetworkKeyManager());
            return false;
        }
        return knm.isKeyPressed(key);
    }

    public NetworkKeyManager getNetworkKeyManager(int id) {
        NetworkKeyManager knm;
        if (!networkManagerList.containsKey(id)) {
            knm = new NetworkKeyManager();
            networkManagerList.put(id, knm);
        } else
            knm = networkManagerList.get(id);
        return knm;
    }

    public LocalKeyManager getLocalKeyManager() {
        return localManager;
    }

    public HashMap<Integer, NetworkKeyManager> getNetworkKeyManagerList() {
        return networkManagerList;
    }
}
