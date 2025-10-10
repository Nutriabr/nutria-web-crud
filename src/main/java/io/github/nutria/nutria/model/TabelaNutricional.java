package io.github.nutria.nutria.model;

public class TabelaNutricional {
    // Atributos
    private Long idIngrediente;
    private double valorEnergeticoKcal;
    private double carboidratosG;
    private double acucaresTotaisG;
    private double acucaresAdicionadosG;
    private double proteinasG;
    private double gordurasTotaisG;
    private double gordurasSaturadasG;
    private double gordurasTransG;
    private double fibraAlimentarG;
    private double sodioMg;
    private Double colesterolMg;
    private Double vitaminaAMcg;
    private Double vitaminaCMg;
    private Double vitaminaDMcg;
    private Double calcioMg;
    private Double ferroMg;
    private Double potassioMg;

    // Construtor vazio
    public TabelaNutricional() {
    }

    // Construtor completo
    public TabelaNutricional(Long idIngrediente, double valorEnergeticoKcal, double carboidratosG, double acucaresTotaisG,
                             double acucaresAdicionadosG, double proteinasG, double gordurasTotaisG, double gordurasSaturadasG,
                             double gordurasTransG, double fibraAlimentarG, double sodioMg, Double colesterolMg, Double vitaminaAMcg,
                             Double vitaminaCMg, Double vitaminaDMcg, Double calcioMg, Double ferroMg, Double potassioMg) {
        this.idIngrediente = idIngrediente;
        this.valorEnergeticoKcal = valorEnergeticoKcal;
        this.carboidratosG = carboidratosG;
        this.acucaresTotaisG = acucaresTotaisG;
        this.acucaresAdicionadosG = acucaresAdicionadosG;
        this.proteinasG = proteinasG;
        this.gordurasTotaisG = gordurasTotaisG;
        this.gordurasSaturadasG = gordurasSaturadasG;
        this.gordurasTransG = gordurasTransG;
        this.fibraAlimentarG = fibraAlimentarG;
        this.sodioMg = sodioMg;
        this.colesterolMg = colesterolMg;
        this.vitaminaAMcg = vitaminaAMcg;
        this.vitaminaCMg = vitaminaCMg;
        this.vitaminaDMcg = vitaminaDMcg;
        this.calcioMg = calcioMg;
        this.ferroMg = ferroMg;
        this.potassioMg = potassioMg;
    }

    // Construtor reduzido
    public TabelaNutricional(Long idIngrediente, double valorEnergeticoKcal, double carboidratosG, double acucaresTotaisG,
                             double acucaresAdicionadosG, double proteinasG, double gordurasTotaisG, double gordurasSaturadasG,
                             double gordurasTransG, double fibraAlimentarG, double sodioMg) {
        this.idIngrediente = idIngrediente;
        this.valorEnergeticoKcal = valorEnergeticoKcal;
        this.carboidratosG = carboidratosG;
        this.acucaresTotaisG = acucaresTotaisG;
        this.acucaresAdicionadosG = acucaresAdicionadosG;
        this.proteinasG = proteinasG;
        this.gordurasTotaisG = gordurasTotaisG;
        this.gordurasSaturadasG = gordurasSaturadasG;
        this.gordurasTransG = gordurasTransG;
        this.fibraAlimentarG = fibraAlimentarG;
        this.sodioMg = sodioMg;
    }

    // Getters e Setters
    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public double getValorEnergeticoKcal() {
        return valorEnergeticoKcal;
    }

    public void setValorEnergeticoKcal(double valorEnergeticoKcal) {
        this.valorEnergeticoKcal = valorEnergeticoKcal;
    }

    public double getCarboidratosG() {
        return carboidratosG;
    }

    public void setCarboidratosG(double carboidratosG) {
        this.carboidratosG = carboidratosG;
    }

    public double getAcucaresTotaisG() {
        return acucaresTotaisG;
    }

    public void setAcucaresTotaisG(double acucaresTotaisG) {
        this.acucaresTotaisG = acucaresTotaisG;
    }

    public double getAcucaresAdicionadosG() {
        return acucaresAdicionadosG;
    }

    public void setAcucaresAdicionadosG(double acucaresAdicionadosG) {
        this.acucaresAdicionadosG = acucaresAdicionadosG;
    }

    public double getProteinasG() {
        return proteinasG;
    }

    public void setProteinasG(double proteinasG) {
        this.proteinasG = proteinasG;
    }

    public double getGordurasTotaisG() {
        return gordurasTotaisG;
    }

    public void setGordurasTotaisG(double gordurasTotaisG) {
        this.gordurasTotaisG = gordurasTotaisG;
    }

    public double getGordurasSaturadasG() {
        return gordurasSaturadasG;
    }

    public void setGordurasSaturadasG(double gordurasSaturadasG) {
        this.gordurasSaturadasG = gordurasSaturadasG;
    }

    public double getGordurasTransG() {
        return gordurasTransG;
    }

    public void setGordurasTransG(double gordurasTransG) {
        this.gordurasTransG = gordurasTransG;
    }

    public double getFibraAlimentarG() {
        return fibraAlimentarG;
    }

    public void setFibraAlimentarG(double fibraAlimentarG) {
        this.fibraAlimentarG = fibraAlimentarG;
    }

    public double getSodioMg() {
        return sodioMg;
    }

    public void setSodioMg(double sodioMg) {
        this.sodioMg = sodioMg;
    }

    public Double getColesterolMg() {
        return colesterolMg;
    }

    public void setColesterolMg(Double colesterolMg) {
        this.colesterolMg = colesterolMg;
    }

    public Double getVitaminaAMcg() {
        return vitaminaAMcg;
    }

    public void setVitaminaAMcg(Double vitaminaAMcg) {
        this.vitaminaAMcg = vitaminaAMcg;
    }

    public Double getVitaminaCMg() {
        return vitaminaCMg;
    }

    public void setVitaminaCMg(Double vitaminaCMg) {
        this.vitaminaCMg = vitaminaCMg;
    }

    public Double getVitaminaDMcg() {
        return vitaminaDMcg;
    }

    public void setVitaminaDMcg(Double vitaminaDMcg) {
        this.vitaminaDMcg = vitaminaDMcg;
    }

    public Double getCalcioMg() {
        return calcioMg;
    }

    public void setCalcioMg(Double calcioMg) {
        this.calcioMg = calcioMg;
    }

    public Double getFerroMg() {
        return ferroMg;
    }

    public void setFerroMg(Double ferroMg) {
        this.ferroMg = ferroMg;
    }

    public Double getPotassioMg() {
        return potassioMg;
    }

    public void setPotassioMg(Double potassioMg) {
        this.potassioMg = potassioMg;
    }

    @Override
    public String toString() {
        return "ID Ingrediente: " + idIngrediente +
                "\nValor energético (kcal): " + valorEnergeticoKcal +
                "\nCarboidratos (g): " + carboidratosG +
                "\nAçúcares totais (g): " + acucaresTotaisG +
                "\nAçúcares adicionados (g): " + acucaresAdicionadosG +
                "\nProteínas (g): " + proteinasG +
                "\nGorduras totais (g): " + gordurasTotaisG +
                "\nGorduras saturadas (g): " + gordurasSaturadasG +
                "\nGorduras trans (g): " + gordurasTransG +
                "\nFibra alimentar (g): " + fibraAlimentarG +
                "\nSódio (mg): " + sodioMg +
                "\nColesterol (mg): " + colesterolMg +
                "\nVitamina A (µg): " + vitaminaAMcg +
                "\nVitamina C (mg): " + vitaminaCMg +
                "\nVitamina D (µg): " + vitaminaDMcg +
                "\nCálcio (mg): " + calcioMg +
                "\nFerro (mg): " + ferroMg +
                "\nPotássio (mg): " + potassioMg;
    }
}
