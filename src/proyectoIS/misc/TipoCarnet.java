package proyectoIS.misc;

public enum TipoCarnet {
    A,A1, A2, AM, B, C, D;

    public static TipoCarnet cast(String s) {
        TipoCarnet r = null;
        switch (s) {
            case "A" -> r = TipoCarnet.A;
            case "A1" -> r = TipoCarnet.A1;
            case "A2" -> r = TipoCarnet.A2;
            case "AM" -> r = TipoCarnet.AM;
            case "B" -> r = TipoCarnet.B;
            case "C" -> r = TipoCarnet.C;
            case "D" -> r = TipoCarnet.D;

        }
        return r;
    }
}
