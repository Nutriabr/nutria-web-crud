package io.github.nutria.nutria.model.entity;

public class TabelaNutricional {
    // Atributos
    private long idIngrediente;
    private double valorEnergeticoKcal;
    private double carboidratosG;
    private double acucaresTotaisG;
    private double acucaresAdicionadosG;
    private double proteinasG;
    private double gordurasTotaisG;
    private double gordurasSaturadasG;
    private double fibraAlimentarG;
    private double sodioMg;
    private double colesterolMg;
    private double vitaminaAMcg;
    private double vitaminaCMg;
    private double vitaminaDMcg;
    private double calcioMg;
    private double ferroMg;
    private double potassioMg;

    // Construtor vazio
    public TabelaNutricional() {
    }

    // Construtor completo
    public TabelaNutricional(long idIngrediente, double valorEnergeticoKcal, double carboidratosG, double acucaresTotaisG,
                             double acucaresAdicionadosG, double proteinasG, double gordurasTotaisG, double gordurasSaturadasG,
                             double fibraAlimentarG, double sodioMg, double colesterolMg, double vitaminaAMcg,
                             double vitaminaCMg, double vitaminaDMcg, double calcioMg, double ferroMg, double potassioMg) {
        this.idIngrediente = idIngrediente;
        this.valorEnergeticoKcal = valorEnergeticoKcal;
        this.carboidratosG = carboidratosG;
        this.acucaresTotaisG = acucaresTotaisG;
        this.acucaresAdicionadosG = acucaresAdicionadosG;
        this.proteinasG = proteinasG;
        this.gordurasTotaisG = gordurasTotaisG;
        this.gordurasSaturadasG = gordurasSaturadasG;
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
    public TabelaNutricional(long idIngrediente, double valorEnergeticoKcal, double carboidratosG, double acucaresTotaisG,
                             double acucaresAdicionadosG, double proteinasG, double gordurasTotaisG, double gordurasSaturadasG,
                             double fibraAlimentarG, double sodioMg) {
        this.idIngrediente = idIngrediente;
        this.valorEnergeticoKcal = valorEnergeticoKcal;
        this.carboidratosG = carboidratosG;
        this.acucaresTotaisG = acucaresTotaisG;
        this.acucaresAdicionadosG = acucaresAdicionadosG;
        this.proteinasG = proteinasG;
        this.gordurasTotaisG = gordurasTotaisG;
        this.gordurasSaturadasG = gordurasSaturadasG;
        this.fibraAlimentarG = fibraAlimentarG;
        this.sodioMg = sodioMg;
    }

    public TabelaNutricional(double valorEnergeticoKcal, double carboidratosG, double acucaresTotaisG,
                             double acucaresAdicionadosG, double proteinasG, double gordurasTotaisG, double gordurasSaturadasG,
                             double fibraAlimentarG, double sodioMg, double colesterolMg, double vitaminaAMcg,
                             double vitaminaCMg, double vitaminaDMcg, double calcioMg, double ferroMg, double potassioMg) {
        this.valorEnergeticoKcal = valorEnergeticoKcal;
        this.carboidratosG = carboidratosG;
        this.acucaresTotaisG = acucaresTotaisG;
        this.acucaresAdicionadosG = acucaresAdicionadosG;
        this.proteinasG = proteinasG;
        this.gordurasTotaisG = gordurasTotaisG;
        this.gordurasSaturadasG = gordurasSaturadasG;
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


    // Getters e Setters
    public long getIdIngrediente() {
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

    public double getColesterolMg() {
        return colesterolMg;
    }

    public void setColesterolMg(double colesterolMg) {
        this.colesterolMg = colesterolMg;
    }

    public double getVitaminaAMcg() {
        return vitaminaAMcg;
    }

    public void setVitaminaAMcg(double vitaminaAMcg) {
        this.vitaminaAMcg = vitaminaAMcg;
    }

    public double getVitaminaCMg() {
        return vitaminaCMg;
    }

    public void setVitaminaCMg(double vitaminaCMg) {
        this.vitaminaCMg = vitaminaCMg;
    }

    public double getVitaminaDMcg() {
        return vitaminaDMcg;
    }

    public void setVitaminaDMcg(double vitaminaDMcg) {
        this.vitaminaDMcg = vitaminaDMcg;
    }

    public double getCalcioMg() {
        return calcioMg;
    }

    public void setCalcioMg(double calcioMg) {
        this.calcioMg = calcioMg;
    }

    public double getFerroMg() {
        return ferroMg;
    }

    public void setFerroMg(double ferroMg) {
        this.ferroMg = ferroMg;
    }

    public double getPotassioMg() {
        return potassioMg;
    }

    public void setPotassioMg(double potassioMg) {
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
