package co.edu.usbcali.banco.modelo;
// Generated 13/04/2018 09:07:59 PM by Hibernate Tools 5.2.8.Final

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cliente generated by hbm2java
 */
@Entity
@Table(name = "cliente", schema = "public")
public class Cliente implements java.io.Serializable {

	private BigDecimal clieId;
	private TipoDocumento tipoDocumento;
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private char activo;
	private Set<Cuenta> cuentas = new HashSet<Cuenta>(0);
	private Set<CuentaRegistrada> cuentaRegistradas = new HashSet<CuentaRegistrada>(0);

	public Cliente() {
	}

	public Cliente(BigDecimal clieId, String nombre, String direccion, String telefono, char activo) {
		this.clieId = clieId;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.activo = activo;
	}

	public Cliente(BigDecimal clieId, TipoDocumento tipoDocumento, String nombre, String direccion, String telefono,
			String email, char activo, Set<Cuenta> cuentas, Set<CuentaRegistrada> cuentaRegistradas) {
		this.clieId = clieId;
		this.tipoDocumento = tipoDocumento;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.activo = activo;
		this.cuentas = cuentas;
		this.cuentaRegistradas = cuentaRegistradas;
	}

	@Id

	@Column(name = "clie_id", unique = true, nullable = false, precision = 30, scale = 0)
	public BigDecimal getClieId() {
		return this.clieId;
	}

	public void setClieId(BigDecimal clieId) {
		this.clieId = clieId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tdoc_id")
	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "direccion", nullable = false)
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "telefono", nullable = false)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "activo", nullable = false, length = 1)
	public char getActivo() {
		return this.activo;
	}

	public void setActivo(char activo) {
		this.activo = activo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	public Set<Cuenta> getCuentas() {
		return this.cuentas;
	}

	public void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	public Set<CuentaRegistrada> getCuentaRegistradas() {
		return this.cuentaRegistradas;
	}

	public void setCuentaRegistradas(Set<CuentaRegistrada> cuentaRegistradas) {
		this.cuentaRegistradas = cuentaRegistradas;
	}

}
