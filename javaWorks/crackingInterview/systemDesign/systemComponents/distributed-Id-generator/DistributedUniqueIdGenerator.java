public class DistributedUniqueIdGenerator {
  
  
  /**
    Now the approach is two fold,
    1. When there are multiple threads in a same machine, we need uniquness
    2. When there are servers which are running in parallel, we need uniquness
    
    
    For 1, we use an AtomicInteger so there is a uniqness among threads
    
    For 2, we can use Internet IP address, Network interfact mac address etc to have a prexfix that will be unique.
    But when the IP configs are different or Network confirgs are setup different we might get into collison.
    So we need a combination of few things as follows,
    
    1. MAC address
    2. Disk seriel numner
    3. CPU process unique ID
    
    All three can form a unique machine ID
  **/

    private static final String machineID = getMachineID();
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    private static String getMachineID() {
        try {
            // Get MAC address of first non-virtual network interface
            NetworkInterface network = null;
            for (NetworkInterface ni : NetworkInterface.getNetworkInterfaces()) {
                if (!ni.isVirtual() && !ni.isLoopback() && !ni.isPointToPoint()) {
                    network = ni;
                    break;
                }
            }
            byte[] mac = network.getHardwareAddress();

            // Get volume serial number of first writable disk
            FileStore store = null;
            for (FileStore fs : FileSystems.getDefault().getFileStores()) {
                if (fs.isWritable()) {
                    store = fs;
                    break;
                }
            }
            String volSerial = store.getAttribute("volume:vsn").toString();

            // Get CPU identifier
            String cpuID = System.getenv("PROCESSOR_IDENTIFIER");

            return toHexString(mac) + "-" + volSerial + "-" + cpuID;
        } catch (Exception ex) {
            return "unknown";
        }
    }

    private static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    public static String getNextID() {
        int localID = idCounter.getAndIncrement();
        return machineID + "-" + localID;
    }

    public static void main(String[] args) {
        System.out.println(getNextID());
        System.out.println(getNextID());
        System.out.println(getNextID());
    }

}
