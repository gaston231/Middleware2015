<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" >
<xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
<xsl:template match="/">
idCliente, codMoneda, importe, fecha , hora 
<xsl:for-each select="//pagos">
 <xsl:variable name="in" select="'fechayhoraCobro'"/>
<xsl:variable name="date" select="'substring($in,1,10)'"/>
         
<xsl:value-of select="concat(idCliente,',',codMoneda,',',importe,',',substring(fechayhoraCobro,1,10),',',substring(fechayhoraCobro,11,16),'&#xA;')"/>
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>