package org.generation.muebleria.dto;

import org.generation.muebleria.model.TipoDireccion;

public class DireccionRequest {
    private TipoDireccion tipoDireccion;
    private String alias;
    private String direccion;
    private String ciudad;
    private String estado;
    private String municipio;
    private String codigoPostal;
    private Boolean esPredeterminada = false;
    private Long idUsuario;

    // Constructores
    public DireccionRequest() {}

    public DireccionRequest(TipoDireccion tipoDireccion, String alias, String direccion,
                            String ciudad, String estado, String municipio,
                            String codigoPostal, Boolean esPredeterminada, Long idUsuario) {
        this.tipoDireccion = tipoDireccion;
        this.alias = alias;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.estado = estado;
        this.municipio = municipio;
        this.codigoPostal = codigoPostal;
        this.esPredeterminada = esPredeterminada;
        this.idUsuario = idUsuario;
    }

    // Getters y Setters
    public TipoDireccion getTipoDireccion() { return tipoDireccion; }
    public void setTipoDireccion(TipoDireccion tipoDireccion) { this.tipoDireccion = tipoDireccion; }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getMunicipio() { return municipio; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }

    public String getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }

    public Boolean getEsPredeterminada() { return esPredeterminada; }
    public void setEsPredeterminada(Boolean esPredeterminada) { this.esPredeterminada = esPredeterminada; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
}