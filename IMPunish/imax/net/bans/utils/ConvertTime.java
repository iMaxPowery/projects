package imax.net.bans.utils;

public class ConvertTime {
    public static String getTimeString(long time) {
        long variacao = time;
        long varsegundos = variacao % 60;
        long varminutos = variacao / 60 % 60;
        long varhoras = variacao / 3600 % 24;
        long vardias = variacao / 86400 % 7;
        long varsemanas = variacao / 604800 % 4;
        long varmeses = variacao / 2419200 % 13;
        long varanos = variacao / 31449600 % 10;

        String segundos = String.valueOf(varsegundos).replaceAll("-", "");
        String minutos = String.valueOf(varminutos).replaceAll("-", "");
        String horas = String.valueOf(varhoras).replaceAll("-", "");
        String dias = String.valueOf(vardias).replaceAll("-", "");
        String semanas = String.valueOf(varsemanas).replaceAll("-", "");
        String meses = String.valueOf(varmeses).replaceAll("-", "");
        String anos = String.valueOf(varanos).replaceAll("-", "");

        if (anos.equals("0") && meses.equals("0") && semanas.equals("0") && dias.equals("0") && horas.equals("0") && minutos.equals("0"))
            return segundos + "s";

        if (anos.equals("0") && meses.equals("0") && semanas.equals("0") && dias.equals("0") && horas.equals("0"))
            return minutos + "m " + segundos + "s";

        if (anos.equals("0") && meses.equals("0") && semanas.equals("0") && dias.equals("0"))
            return horas + "h " + minutos + "m " + segundos + "s";

        if (anos.equals("0") && meses.equals("0") && semanas.equals("0"))
            return dias + "d " + horas + "h " + minutos + "m";

        if (anos.equals("0") && meses.equals("0"))
            return semanas + "sm " + dias + "d " + horas + "h " + minutos + "m";

        if (anos.equals("0"))
            return meses + "ms " + semanas + "sm " + dias + "d " + horas + "h " + minutos + "m ";

        return anos + "a " + meses + "ms " + semanas + "sm " + dias + "d " + horas + "h " + minutos + "m ";
    }

    public static Long getTimeLong(String time) {
        long value = 0;
        String[] timer = time.split(" ");
        for (int i = 0; i < timer.length; i++){
            try {
                if (timer[i].contains("m"))
                    value += Integer.valueOf(timer[i].replace("m", "")) * 60;
                if (timer[i].contains("h"))
                    value += Integer.valueOf(timer[i].replace("h", "")) * 60 * 60;
                if (timer[i].contains("d"))
                    value += Integer.valueOf(timer[i].replace("d", "")) * 60 * 60 * 24;
                if (timer[i].contains("sm"))
                    value += Integer.valueOf(timer[i].replace("sm", "")) * 86400;
                if (timer[i].contains("ms"))
                    value += Integer.valueOf(timer[i].replace("ms", "")) * 604800;
                if (timer[i].contains("s"))
                    value += Integer.valueOf(timer[i].replace("s", ""));
            }catch (Exception e){

            }
        }
        return value;
    }
}
