package life.qbic.portal.utils;

public class DashboardUtil {
	/**
	 * Transforms bytes into human readable byte count eg.  110592:   110.6 kB (si = true)  108.0 KiB (si =false)
	 * @param bytes
	 * @param si
	 * @return bytes in readable count and unit
	 */
	public static String humanReadableByteCount(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
}
