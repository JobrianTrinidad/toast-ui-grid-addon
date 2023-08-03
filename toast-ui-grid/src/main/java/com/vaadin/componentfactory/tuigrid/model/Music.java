package com.vaadin.componentfactory.tuigrid.model;

/*-
 * #%L
 * TuiGrid
 * %%
 * Copyright (C) 2021 Vaadin Ltd
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import elemental.json.Json;
import elemental.json.JsonObject;

import java.util.Objects;
import java.util.Optional;

public class Music {
    private long Id;
    private String name;
    private String artist;
    private String type;
    private String genre;
    private String release;
    private String price;
    private String download;
    private String listen;

    public Music(String name, String artist,
                 String type, String genre,
                 String release, String price,
                 String download, String listen) {
        this.name = name;
        this.artist = artist;
        this.type = type;
        this.genre = genre;
        this.release = release;
        this.price = price;
        this.download = download;
        this.listen = listen;
    }

    public long getId() {
        return Id;
    }

    public void setKey(long Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getListen() {
        return listen;
    }

    public void setListen(String listen) {
        this.listen = listen;
    }

    public Music(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Music other = (Music) obj;
        return Objects.equals(Id, other.Id);
    }
    public String toJSON() {
        JsonObject js = Json.createObject();
        Optional.ofNullable(getId()).ifPresent(v -> js.put("id", v));
        Optional.ofNullable(getName()).ifPresent(v -> js.put("name", v));
        Optional.ofNullable(getArtist()).ifPresent(v -> js.put("artist", v.toString()));
        Optional.ofNullable(getType()).ifPresent(v -> js.put("type", v.toString()));
        Optional.ofNullable(getGenre()).ifPresent(v -> js.put("genre", v));
        Optional.ofNullable(getRelease()).ifPresent(v -> js.put("release", v));
        Optional.ofNullable(getPrice()).ifPresent(v -> js.put("price", v));
        Optional.ofNullable(getDownload()).ifPresent(v -> js.put("download", v));
        Optional.ofNullable(getListen()).ifPresent(v -> js.put("listen", v));

//        Optional.ofNullable(getEditable())
//                .ifPresent(
//                        v -> {
//                            if (v && (getUpdateTime() != null || getRemove() != null)) {
//                                JsonObject optionsJs = Json.createObject();
//                                Optional.ofNullable(getUpdateTime()).ifPresent(u -> optionsJs.put("updateTime", u));
//                                Optional.ofNullable(getRemove()).ifPresent(r -> optionsJs.put("remove", r));
//                                js.put("editable", optionsJs);
//                            } else {
//                                js.put("editable", v);
//                            }
//                        });

//        Optional.ofNullable(getTitle()).ifPresent(v -> js.put("title", v));
//        Optional.ofNullable(getClassName()).ifPresent(v -> js.put("className", v));
        return js.toJson();
    }

    public Music() {

    }
}
