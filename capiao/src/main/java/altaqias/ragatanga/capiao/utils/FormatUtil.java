package altaqias.ragatanga.capiao.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.StringUtils;

public abstract class FormatUtil {

	private static final String MASK_CPF = "###.###.###-##";
	private static final String MASK_CEP = "#####-###";
	private static final String MASK_CNPJ = "##.###.###/####-##";
	private static final String MASK_FONE = "(##) ####-####";
	private static final String MASK_DINHEIRO = "#,##0.00";
	private static final String MASK_DECIMAL = "#,##0.00";
	private static final String MASK_DECIMAL_3 = "#,##0.000";
	private static final String MASK_DINHEIRO_ASTERISCOS = "**.***.***.***,**";
	
	public static final String MASK_DATE = "dd/MM/yyyy";
	public static final String MASK_TIMESTAMP = "dd/MM/yyyy HH:mm:ss";
	public static final String MASK_DATE_DB = "yyyy-MM-dd";
	public static final String MASK_TIMESTAMP_DB = "yyyy-MM-dd HH:mm:ss";
	
	public static String normalizar(String texto){
		texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
		texto = texto.replaceAll("[^\\p{ASCII}]", "");
		texto = texto.replaceAll("\\W", "");
		texto = texto.replaceAll("[_]", "");
		return texto;
	}

	public static String normalizarDecimal(String texto){
		texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
		texto = texto.replaceAll("[^0123456789,]", "");
		texto = texto.replaceAll(",",".");
		return texto;
	}

	public static String formatarDocumento(String documento){
		String docNormalizado = normalizar(documento);
		if(docNormalizado.length() > 11){
			return formatarCNPJ(docNormalizado);
		}
		return formatarCPF(docNormalizado);
	}
	
	public static String formatarDocumentoComLabel(String documento){
		String docNormalizado = normalizar(documento);
		if(docNormalizado.length() > 11){
			return "CNPJ: " + formatarCNPJ(docNormalizado);
		}
		return "CPF: " + formatarCPF(docNormalizado);
		
	}

	private static String formatarCNPJ(String cnpj){
		return formatar(MASK_CNPJ, cnpj);
	}

	private static String formatarCPF(String cpf){
		return formatar(MASK_CPF, cpf);
	}

	public static String formatarCep(String cep){
		String cepNormalizado = normalizar(cep);
		return formatar(MASK_CEP, cepNormalizado);
	}
	
	public static String formatarTelefone(String fone){
		String foneNormalizado = normalizar(fone);
		return formatar(MASK_FONE, foneNormalizado);
	}

	public static String formatarDinheiro(Double dinheiro){
		DecimalFormat d = new DecimalFormat(MASK_DINHEIRO);
		return d.format(dinheiro);
	}
	
	public static String formatarDinheiro(String dinheiro){
		double valor = new Double(dinheiro);
		DecimalFormat d = new DecimalFormat(MASK_DINHEIRO);
		return d.format(valor);
	}

	public static String formatarDinheiroAsteriscos(String dinheiro) {
		String valorFormatado = formatarDinheiro(dinheiro);
		return StringUtils.leftPad(valorFormatado, MASK_DINHEIRO_ASTERISCOS.length(), MASK_DINHEIRO_ASTERISCOS);
	}
	
	public static String formatarDinheiroAsteriscos(Double dinheiro) {
		String valorFormatado = formatarDinheiro(dinheiro);
		return StringUtils.leftPad(valorFormatado, MASK_DINHEIRO_ASTERISCOS.length(), MASK_DINHEIRO_ASTERISCOS);
	}
	
	public static String formatarBigDecimal(BigDecimal bigDecimal){
		DecimalFormat formatter =  new DecimalFormat(MASK_DECIMAL);
		return formatter.format(bigDecimal);
	}
	
	public static String formatarBigDecimal(BigDecimal bigDecimal, boolean tresCasas){
		DecimalFormat formatter =  new DecimalFormat(tresCasas?MASK_DECIMAL_3:MASK_DECIMAL);
		return formatter.format(bigDecimal);
	}
	
	private static String formatar(String mascara, String texto){
		MaskFormatter mask;
		try {
			mask = new MaskFormatter(mascara);
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(texto);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String formatarData(Date data, String mask){
		return new SimpleDateFormat(mask).format(data);
	}
	
	public static String formatarData(Date data){
		return new SimpleDateFormat(MASK_DATE).format(data);
	}

	public static String formatarDataExtenso(Date data) {
		SimpleDateFormat dfmt = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy");
		return dfmt.format(data);
	}
	
	
	public static String formataCodigoCliente(String codigo){
		return StringUtils.leftPad(codigo, 6, '0');
	}
	
	public static String formataCodigoApresentante(String codigo){
		return StringUtils.leftPad(codigo, 6, '0');
	}
	
}
