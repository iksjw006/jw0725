package main.report.styles;

public class MenuStyle {

    private static int borderLength = 50;
    private static String borderCharacter = "#";
    public static String border = String.format("%0" + borderLength + "d\n", 0).replace("0", borderCharacter);
    public static String columnWidthLargeIndented = "\t%-42s %s%n";
    public static String columnWidthLargeIndentedWithPercent = "\t%-42s %s%%%n";
    public static String columnWidthLargeIndentedWithDate = "\t%-42s %tD%n";
    public static String columnWidthSmallString = "%-24s %s%n";
    public static String columnWidthSmallCurrency = "%-24s $%,.2f%n";
    public static String columnWidthSmallPercent = "%-24s %s%%%n";
    public static String columnWidthSmallDate = "%-24s %tD%n";
    public static String columnWidthSmallInteger = "%-24s %d\n";

    public static String agreementReady = "AGREEMENT PREVIEW READY";
    public static String agreementNotReady = "MISSING REQUIRED FIELDS";
    public static String agreementValueSet = "VALUE SET";
    public static String agreementValueNotSet = "REQUIRED";

    public static String toolMenuColumns = "%-15s %-20s %-20s%n";

}
