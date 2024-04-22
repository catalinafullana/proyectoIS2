package proyectoIS.misc;

public enum Preferencia_clase {
    MANYANA, TARDE, AMBOS;

    public static Preferencia_clase cast(String preferenciaClase) {
        Preferencia_clase p = null;

        switch (preferenciaClase){
            case "MAÑANA" -> p = Preferencia_clase.MANYANA;
            case "TARDE" -> p = Preferencia_clase.TARDE;
            case "AMBOS" -> p = Preferencia_clase.AMBOS;
        }
        return p;
    }
}
