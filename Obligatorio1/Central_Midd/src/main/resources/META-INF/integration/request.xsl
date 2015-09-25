<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"				
				xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"				
				version="2.0">
<xsl:template match="arg0">

	<xsl:output method="xml" omit-xml-declaration="yes" indent="yes"/>
	<xsl:param name="fecha" select="fechaCobro"/>
	<xsl:for-each select ="pagos">
		
		<xsl:choose>
			<xsl:when test="nombreGestion='Entradas'">
						
				<ventaEntrada xmlns="http://middleware/">				
					<arg0><xsl:value-of select="substring(datoAdicional,1,2)"/></arg0>
					<arg1><xsl:call-template name="currency2"/></arg1>
					<arg2><xsl:value-of select="substring(datoAdicional,4,5)"/></arg2>
					<arg3><xsl:value-of select="$fecha"/></arg3>
				
				</ventaEntrada>			
			</xsl:when>
			
			<xsl:when test="nombreGestion='Factura'">
				<PagarFactura xmlns="http://middleware/">				
					<arg0><xsl:value-of select="identificadorPago"/></arg0>
					<arg1> <xsl:call-template name="currency"/> </arg1>
					<arg2><xsl:value-of select="monto"/></arg2>
					<arg3><xsl:value-of select="$fecha"/></arg3>
				
				</PagarFactura>
			</xsl:when>
			<xsl:otherwise>
   
			</xsl:otherwise>
		</xsl:choose>
	</xsl:for-each>
	
	
</xsl:template>

	<xsl:template name="currency">
			<xsl:choose>
				<xsl:when test="codigoMoneda = 'UYU'">
					1
				</xsl:when>
				<xsl:when test="codigoMoneda = 'USD'">
					2
				</xsl:when>
			</xsl:choose>
		</xsl:template>
		
	<xsl:template name="currency2">
			<xsl:choose>
				<xsl:when test="codigoMoneda = 'UYU'">
					854
				</xsl:when>
				<xsl:when test="codigoMoneda = 'USD'">
					840
				</xsl:when>
			</xsl:choose>
	</xsl:template>


</xsl:stylesheet>
