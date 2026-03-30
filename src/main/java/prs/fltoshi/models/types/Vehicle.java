package prs.fltoshi.models.types;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import prs.fltoshi.models.common.CrewService;
import prs.fltoshi.models.common.EconomyRewards;
import prs.fltoshi.models.common.RepairStats;
import prs.fltoshi.models.common.WeaponSystem;
import prs.fltoshi.models.common.EngineStats;
import prs.fltoshi.models.common.Modification;

/*
* Базовый класс для всех видов техники
*/
@Data
@SuperBuilder
@NoArgsConstructor
public class Vehicle {
    private String identifier;
    private String country;
    private String vehicleType;
    
    @lombok.Builder.Default
    private List<String> vehicleSubType = new ArrayList<>();
    
    private int era;
    
    // БРы (Jackson сам поймет arcade_br -> arcadeBr с настройкой SnakeCase)
    private double arcadeBr;
    private double realisticBr;
    private double realisticGroundBr;
    private double simulatorBr;
    private double simulatorGroundBr;

    // --- Экономика ---
    @JsonProperty("req_exp")
    private int expValue;
    
    @JsonProperty("value")
    private int slValue;
    
    @JsonProperty("ge_cost")
    private int geValue;

    // --- Флаги ---
    private boolean isPremium;
    
    @JsonProperty("squadron_vehicle")
    private boolean isSquadron;
    
    private boolean isPack;
    private boolean onMarketplace;

    // --- Экипаж и обучение ---
    private CrewService crew;

    // --- Экономика ---
    private EconomyRewards rewards;
    private RepairStats repair;

    // --- Оружие ---
    private WeaponSystem weaponSystem;

    // --- Визуализация и прочее ---
    private String vehicleSingleImageUrl;
    private String vehicleTechImageUrl;
    
    private String releaseDate;
    private String version;
    private int visibility;
    private String requiredVehicle;
    private double mass;

    // --- Бронирование (мм) ---
    private int[] hullArmor;
    private int[] turretArmor;

    // --- Прочее ---
    private List<String> versions;
    private boolean hasCustomizableWeapons;

    // --- Двигатель и модификации ---
    private EngineStats engine;
    private List<Modification> modifications;
}
